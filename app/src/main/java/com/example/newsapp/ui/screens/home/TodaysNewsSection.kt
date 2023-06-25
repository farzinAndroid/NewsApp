package com.example.newsapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newsapp.R
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.ui.component.NewsItem
import com.example.newsapp.ui.component.PreLoadAnimation
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.spacing
import com.example.newsapp.viewmodel.HomeViewModel

@Composable
fun TodaysNewsSection(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {

    var newsList by remember {
        mutableStateOf<List<TopNewsModel>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }





    val newsListResult by homeViewModel.newsBasedOnSource.collectAsState()
    when (newsListResult) {
        is NetworkResult.Success -> {

            newsList = newsListResult.data ?: emptyList()
            loading = false

        }

        is NetworkResult.Error -> {
            Log.e("TAG", "newsListResult ${newsListResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    if (loading){
        PreLoadAnimation()
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = MaterialTheme.spacing.medium)
                    .padding(bottom = MaterialTheme.spacing.extraSmall),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = stringResource(R.string.today_news),
                    style = Typography.displayLarge,
                    color = MaterialTheme.colorScheme.darktext,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.biggerMedium)
                        .padding(bottom = MaterialTheme.spacing.medium)
                )

            }

            LazyColumn(
                modifier =Modifier
                    .fillMaxWidth()
                    .height(800.dp)
            ){
                items(newsList){news->
                    NewsItem(news)
                }
            }

        }
    }



}