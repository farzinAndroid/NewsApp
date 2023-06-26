package com.example.newsapp.ui.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.ui.component.NewsItem
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.roundedShape
import com.example.newsapp.ui.theme.spacing
import com.example.newsapp.viewmodel.SearchViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchSection(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {

    var newsText by remember {
        mutableStateOf("")
    }

    /*LaunchedEffect(true){
        searchViewModel.getNewsBySearch(newsText)
    }*/

    var searchNewsList by remember {
        mutableStateOf<List<TopNewsModel>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    var state by remember(newsText) {
        mutableStateOf(newsText)
    }

    var searchJob by remember { mutableStateOf<Job?>(null) }
    var courotinScope = rememberCoroutineScope()


    val newsBySearchResult by searchViewModel.newsBySearch.collectAsState()
    when (newsBySearchResult) {
        is NetworkResult.Success -> {
            searchNewsList = newsBySearchResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            Log.e("TAG", "newsBySearchResult error : ${newsBySearchResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }

    }


    Column() {

        BasicTextField(
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.darktext,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                lineHeight = 25.sp
            ),
            value = newsText,
            onValueChange = {
                newsText = it
                state = it
                searchJob?.cancel()
                searchJob = courotinScope.launch {
                    delay(1000) // Wait for 500ms before calling the search API
                    if (searchViewModel.validateQuery(newsText)) {
                        searchViewModel.getNewsBySearch(newsText)
                    }

                }

            },
            cursorBrush = Brush.horizontalGradient(
                listOf(
                    MaterialTheme.colorScheme.darktext.copy(0.5f),
                    MaterialTheme.colorScheme.darktext.copy(0.5f)
                )
            ),
            modifier = Modifier
                .padding(
                    top = MaterialTheme.spacing.medium,
                    start = MaterialTheme.spacing.semiLarge,
                    end = MaterialTheme.spacing.semiLarge
                )
                .fillMaxWidth(),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            brush = Brush.verticalGradient(
                                listOf(
                                    Color(0xFFe51324),
                                    Color(0xFF83000B)
                                )
                            ),
                            width = 1.dp,
                            shape = MaterialTheme.roundedShape.biggerSmall
                        )
                        .padding(MaterialTheme.spacing.medium)
                ) {
                    if (newsText.isNullOrBlank() || newsText.isNullOrEmpty()) {
                        Text(
                            text = stringResource(R.string.search_txt),
                            color = MaterialTheme.colorScheme.darktext.copy(0.5f),
                            style = Typography.displaySmall
                        )
                    }
                    innerTextField()
                }
            }
        )


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .height(800.dp)
                .padding(bottom = 64.dp)
        ) {

            items(searchNewsList) { news ->
                NewsItem(news = news)
            }

        }

    }


}