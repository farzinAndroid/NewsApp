package com.example.newsapp.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.newsapp.R
import com.example.newsapp.ui.theme.bottomBar
import com.example.newsapp.ui.theme.elevation
import com.example.newsapp.ui.theme.hammenucolor
import com.example.newsapp.ui.theme.newstodayText
import com.example.newsapp.ui.theme.roundedShape
import com.example.newsapp.ui.theme.spacing

@Composable
fun AppHeader() {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp),
        elevation = CardDefaults.cardElevation(MaterialTheme.elevation.small),
        shape = MaterialTheme.roundedShape.biggerSmall,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.bottomBar
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = MaterialTheme.spacing.medium),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(R.drawable.menu),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.hammenucolor,
                modifier = Modifier
                    .size(30.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
            ) {
                Image(
                    painter = if (isSystemInDarkTheme()) painterResource(R.drawable.news_dark) else painterResource(R.drawable.news),
                    contentDescription = "",
                    modifier = Modifier
                        .size(50.dp)
                        .padding(horizontal = MaterialTheme.spacing.extraSmall),
                )

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .padding(start = MaterialTheme.spacing.small)
                        .height(70.dp)
                ) {
                    Text(
                        text = stringResource(R.string.breaking),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.newstodayText,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = stringResource(R.string.news),
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.newstodayText,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )
                }
            }


            Icon(
                painter = painterResource(R.drawable.notif),
                contentDescription = "",
                tint = MaterialTheme.colorScheme.hammenucolor,
                modifier = Modifier
                    .size(26.dp)
            )

        }
    }


}