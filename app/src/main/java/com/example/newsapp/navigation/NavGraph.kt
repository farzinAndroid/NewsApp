package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.ui.screens.category.CategoryScreen
import com.example.newsapp.ui.screens.home.HomeScreen
import com.example.newsapp.ui.screens.search.SearchScreen
import com.example.newsapp.ui.screens.splash.SplashScreen
import com.example.newsapp.ui.screens.webpage.WebPageScreen

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
            CategoryScreen(navController)
        }

        composable(Screens.SearchScreen.route){
            SearchScreen(navController)
        }

        composable(Screens.SplashScreen.route){
            SplashScreen(navController)
        }

        composable(
            route = Screens.WebPageScreen.route + "?url={url}",
            arguments = listOf(
                navArgument("url"){
                    type = NavType.StringType
                    defaultValue = ""
                    nullable = true
                }
            )
        ){ it ->
            val url =it.arguments?.getString("url")

            url?.let { myUrl->
                WebPageScreen(url = myUrl)
            }

        }

    }


}