package com.example.newsapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)


val ColorScheme.bottomBar: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF3F3F3F) else Color(0xFFEEEEEE)

val ColorScheme.selectedbottomBar: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFF83232) else Color(0xFFE90000)

val ColorScheme.unselectedbottomBar: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFF8F8F8F) else Color(0xFF8F8F8F)

val ColorScheme.hammenucolor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color(0xFFFFFEFE) else Color(0xFF000000)





//val ColorScheme.selectedBottomBar: Color
//    @Composable
//    get() = if (isSystemInDarkTheme()) Color(0xFFCFD4DA) else Color(0xFF000000)