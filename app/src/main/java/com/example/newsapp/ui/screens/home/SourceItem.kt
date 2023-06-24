package com.example.newsapp.ui.screens.home

import androidx.compose.animation.core.AnimationConstants
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.DefaultMarqueeIterations
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.selectedbottomBar
import com.example.newsapp.ui.theme.spacing

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SourceItem(
    index: Int,
    selectedTabPosition: Int,
    item: SourceModel,
    onClick: () -> Unit,
) {

    val backGround = if (index == selectedTabPosition) {
        Modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.Transparent,
                    Color.Transparent,
                    Color.Transparent,
                    item.color.copy(0.5f)
                )
            )
        )
    }else{
        Modifier.background(Color.Transparent)
    }

    Box(
        modifier = backGround,
    ) {

        Column(
            modifier = Modifier
                .height(180.dp)
                .width(100.dp)
                .padding(MaterialTheme.spacing.small)
                .clickable {
                    onClick()
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ImageWithBorder(image = item.painter, borderColor = item.color)

            Text(
                text = item.text,
                style = Typography.titleLarge,
                color = if (index == selectedTabPosition) item.color else MaterialTheme.colorScheme.darktext,
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