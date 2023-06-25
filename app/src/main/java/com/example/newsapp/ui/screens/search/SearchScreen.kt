package com.example.newsapp.ui.screens.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun SearchScreen(navController: NavController) {
    Search()
}

@Composable
fun Search() {

    Text(text = "Search")
}