package com.example.newsapp.ui.screens.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsapp.ui.component.AppHeader
import com.example.newsapp.viewmodel.HomeViewModel

@Composable
fun HomeScreen(navController: NavHostController) {
    Home(navController)
}

@Composable
fun Home(navController: NavHostController, homeViewModel: HomeViewModel = hiltViewModel()) {


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 60.dp)
    ) {

        item { AppHeader() }
        item { SourceRow(navController) }


    }


}