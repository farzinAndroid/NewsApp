package com.example.newsapp.ui.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.navigation.Screens
import com.example.newsapp.ui.component.NewsItem
import com.example.newsapp.ui.component.PreLoadAnimation
import com.example.newsapp.viewmodel.SearchViewModel

@Composable
fun SearchScreen(navController: NavController) {
    Search(navController)
}

@Composable
fun Search(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel(),
) {


    var newsText by remember {
        mutableStateOf("")
    }


    var searchNewsList by remember {
        mutableStateOf<List<TopNewsModel>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }


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


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 64.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        item {
            TextFieldSection(searchViewModel) {
                newsText = it
            }
        }


        if (loading && newsText.isNotBlank()) {
            item { PreLoadAnimation() }
        } else {
            items(searchNewsList) { news ->
                NewsItem(news = news) {
                    navController.navigate(Screens.WebPageScreen.route + "?url=${news.url}")
                }
            }
        }



    }
}