package com.example.newsapp.navigation

sealed class Screens(val route:String) {

    object HomeScreen : Screens(route = "home_screen")
    object CategoryScreen : Screens(route = "category_screen")
    object SearchScreen : Screens(route = "search_screen")
    object SplashScreen : Screens(route = "splash_screen")

}