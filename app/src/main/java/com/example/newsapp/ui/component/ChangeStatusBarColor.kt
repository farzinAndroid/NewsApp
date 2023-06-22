package com.example.newsapp.ui.component

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.navigation.Screens
import com.example.newsapp.ui.theme.bottomBar
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChangeStatusBarColor(navController:NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val systemUiController = rememberSystemUiController()


    val stausbarcolor = if (isSystemInDarkTheme()){
        Color.Black
    }else{
        MaterialTheme.colorScheme.bottomBar
    }

    when (navBackStackEntry?.destination?.route) {
        Screens.HomeScreen.route -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = stausbarcolor
                )
            }
        }
        else -> {
            SideEffect {
                systemUiController.setStatusBarColor(
                    color = stausbarcolor
                )
            }
        }
    }

}