package com.example.newsapp.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.NewsAppTheme
import com.example.newsapp.ui.theme.selectedbottomBar

@Composable
fun ImageWithBorder(
    image:Painter,
    borderColor:Color
) {

    Box(
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape)
            .border(width = 2.dp, color = borderColor, shape = CircleShape)
            .padding(4.dp)

    ){
        Image(
            painter = image,
            contentDescription ="",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
        )
    }




}


@Preview(showBackground = true)
@Composable
fun ImageWithBorderPreview() {
    NewsAppTheme {
        ImageWithBorder(
            image = painterResource(R.drawable.bbc),
            borderColor = MaterialTheme.colorScheme.selectedbottomBar
        )
    }
}