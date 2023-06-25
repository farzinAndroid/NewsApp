package com.example.newsapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.newsapp.R

@Composable
fun GlobalNewsAnimation() {

    val globalComposition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.global_news))


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        LottieAnimation(
            composition = globalComposition,
            iterations = 1,
            modifier = Modifier
                .size(400.dp)
        )


    }


}