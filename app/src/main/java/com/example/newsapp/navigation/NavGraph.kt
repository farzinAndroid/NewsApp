package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.ui.screens.category.CategoryScreen
import com.example.newsapp.ui.screens.home.HomeScreen
import com.example.newsapp.ui.screens.search.SearchScreen

@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination =  Screens.HomeScreen.route
    ){

        composable(Screens.HomeScreen.route){
            HomeScreen(navController)
        }

        composable(Screens.CategoryScreen.route){
            CategoryScreen()
        }

        composable(Screens.SearchScreen.route){
            SearchScreen()
        }

    }


}