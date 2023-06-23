package com.example.newsapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.navigation.BottomBar
import com.example.newsapp.navigation.NavGraph
import com.example.newsapp.ui.component.ChangeStatusBarColor
import com.example.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsAppTheme {

                val navController = rememberNavController()

                ChangeStatusBarColor(navController = navController)
                CompositionLocalProvider(LocalLayoutDirection.provides(LayoutDirection.Ltr)) {

                    Scaffold(
                        bottomBar = {
                            BottomBar(navController, onItemClick = {
                                navController.navigate(it.route){

                                }
                            })
                        },
                    ) {

                        NavGraph(navController = navController)

                    }

                }

            }
        }
    }
}