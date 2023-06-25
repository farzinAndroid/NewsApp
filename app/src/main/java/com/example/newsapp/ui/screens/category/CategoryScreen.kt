package com.example.newsapp.ui.screens.category

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.ui.component.AppHeader

@Composable
fun CategoryScreen(navController: NavController) {

    Category(navController)

}

@Composable
fun Category(navController:NavController) {

    LazyColumn(
        modifier = Modifier
            .padding(bottom = 64.dp)
    ){

        item { AppHeader() }
        item { CategoryRow(navController) }

    }

}