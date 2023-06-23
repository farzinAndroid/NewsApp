package com.example.newsapp.ui.screens.home

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter

data class SourceModel(
    val painter: Painter,
    val text:String,
    val color: Color,
    val id:Int
)
