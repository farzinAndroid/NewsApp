package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.newsapp.ui.screens.category.CategoryScreen
import com.example.newsapp.ui.screens.home.HomeScreen
import com.example.newsapp.ui.screens.search.SearchScreen
import com.example.newsapp.ui.screens.splash.SplashScreen

@Composable
fun NavGraph(navController: NavHostController) {


    NavHost(
        navController = navController,
        startDestination =  Screens.SplashScreen.route
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

        composable(Screens.SplashScreen.route){
            SplashScreen(navController)
        }

    }


}