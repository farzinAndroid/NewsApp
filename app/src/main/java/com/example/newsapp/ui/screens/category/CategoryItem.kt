package com.example.newsapp.ui.screens.category

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.roundedShape
import com.example.newsapp.ui.theme.selectedbottomBar
import com.example.newsapp.ui.theme.spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CategoryItem(
    index: Int,
    selectedTabPosition: Int,
    item: CategoryModel,
    onClick: () -> Unit,
) {

    var boxColor  = Color.Transparent
    var textColor  = MaterialTheme.colorScheme.darktext
    var iconColor  = MaterialTheme.colorScheme.selectedbottomBar

    if (selectedTabPosition == index){
        boxColor = MaterialTheme.colorScheme.selectedbottomBar.copy(0.7f)
        textColor = MaterialTheme.colorScheme.selectedbottomBar
        iconColor = Color.White
    }


    Box(
        modifier = Modifier
            .height(180.dp)
            .clickable {
                onClick()
            }
            .padding(horizontal = MaterialTheme.spacing.medium),
        contentAlignment = Alignment.TopCenter
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .clip(MaterialTheme.roundedShape.large)
                    .background(boxColor)
                    .size(90.dp),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    painter = item.painter,
                    contentDescription = "",
                    tint = iconColor,
                    modifier = Modifier
                        .size(50.dp)
                )

            }


            Text(
                text = item.text,
                style = Typography.headlineSmall,
                color = textColor,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .basicMarquee(
                        animationMode = MarqueeAnimationMode.Immediately,
                        iterations = Int.MAX_VALUE,
                        spacing = MarqueeSpacing(10.dp),
                        delayMillis = 0,
                        velocity = 50.dp,
                    ),
                textAlign = TextAlign.Center
            )
        }

    }



}