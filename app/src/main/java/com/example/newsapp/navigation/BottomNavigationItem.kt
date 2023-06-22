package com.example.newsapp.navigation

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem(
    val name:String,
    val icon:Painter,
    val route:String
)