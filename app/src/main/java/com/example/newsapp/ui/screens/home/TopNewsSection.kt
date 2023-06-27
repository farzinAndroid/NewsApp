package com.example.newsapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.ui.component.PreLoadAnimation
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.spacing
import com.example.newsapp.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopNewsSection(
    source:String,
    homeViewModel: HomeViewModel = hiltViewModel(),
    navController:NavController
) {

    LaunchedEffect(true){
        homeViewModel.getTopNewsData(source)
        homeViewModel.getNewsDataBasedOnSource(source)
    }

    var topNewsList by remember {
        mutableStateOf<List<TopNewsModel>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }

    val pagerState = rememberPagerState()


    val topNewsSliderResult by homeViewModel.topHeadline.collectAsState()
    when (topNewsSliderResult) {
        is NetworkResult.Success -> {

            topNewsList = topNewsSliderResult.data ?: emptyList()
            loading = false

        }

        is NetworkResult.Error -> {
            Log.e("TAG", "topNewsSliderResultError ${topNewsSliderResult.message}")
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
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = stringResource(R.string.top_news),
                    style = Typography.displayLarge,
                    color = MaterialTheme.colorScheme.darktext,
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.biggerMedium)
                        .padding(bottom = MaterialTheme.spacing.medium)
                )

                Text(
                    text = stringResource(R.string.see_all),
                    color = Color.Gray,
                    style = Typography.displaySmall,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier
                        .padding(end = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.biggerMedium)
                        .padding(bottom = MaterialTheme.spacing.medium)
                )
            }

            TopSliderSection()


            TodaysNewsSection(navController = navController)

        }
    }


}