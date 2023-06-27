package com.example.newsapp.ui.screens.category

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.navigation.Screens
import com.example.newsapp.ui.component.NewsItem
import com.example.newsapp.ui.component.PreLoadAnimation
import com.example.newsapp.viewmodel.CategoryViewModel

@Composable
fun NewsBasedOnCategorySection(
    category: String,
    navController: NavController,
    categoryViewModel: CategoryViewModel = hiltViewModel(),
) {

    LaunchedEffect(true) {
        categoryViewModel.getNewsBasedOnCategory(category)
    }

    var topNewsCategoryList by remember {
        mutableStateOf<List<TopNewsModel>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }


    val topNewsCategoryResult by categoryViewModel.newsBasedOnCategory.collectAsState()
    when (topNewsCategoryResult) {
        is NetworkResult.Success -> {
            topNewsCategoryList = topNewsCategoryResult.data ?: emptyList()
            loading = false
        }

        is NetworkResult.Error -> {
            loading = false
            Log.e("TAG", "topNewsCategoryResult error :${topNewsCategoryResult.message}")
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }


    if (loading) {
        PreLoadAnimation()


    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .height(800.dp),
        ) {
            items(topNewsCategoryList) { news ->
                NewsItem(news){
                    navController.navigate(Screens.WebPageScreen.route + "?url=${news.url}")
                }
            }
        }
    }


}