package com.example.newsapp.ui.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.newsapp.navigation.Screens
import com.example.newsapp.ui.theme.splashBG
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    Splash()

    LaunchedEffect(true) {
        delay(3000)
        navController.navigate(Screens.HomeScreen.route)
    }

}


@Composable
fun Splash() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.splashBG)
    ) {


        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            BreakingNewsAnimation()
        }



    }

}

