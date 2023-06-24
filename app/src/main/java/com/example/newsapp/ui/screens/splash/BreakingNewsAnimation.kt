package com.example.newsapp.ui.screens.splash

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapp.R

@Composable
fun BreakingNewsAnimation() {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.news1))

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,


    )

}