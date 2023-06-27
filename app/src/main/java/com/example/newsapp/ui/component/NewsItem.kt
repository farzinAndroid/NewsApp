package com.example.newsapp.ui.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.R
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.roundedShape
import com.example.newsapp.ui.theme.spacing
import com.example.newsapp.utils.DateChanger

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NewsItem(
    news: TopNewsModel,
    onClick: () -> Unit,
) {

    val place_holder = if (isSystemInDarkTheme()) {
        painterResource(R.drawable.place_holder_dark)
    } else {
        painterResource(R.drawable.place_holder_light)
    }


    Row(
        modifier = Modifier
            .padding(vertical = MaterialTheme.spacing.small)
            .padding(start = MaterialTheme.spacing.small)
            .fillMaxWidth()
            .height(120.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
    ) {

        Column(
            modifier = Modifier
                .weight(0.3f)
        ) {

            Image(
                painter = if (news.urlToImage.isNullOrEmpty()) place_holder else rememberAsyncImagePainter(
                    news.urlToImage
                ),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(120.dp)
                    .clip(MaterialTheme.roundedShape.small)
            )

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .weight(0.7f)
                .padding(MaterialTheme.spacing.extraSmall),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = news.title,
                style = Typography.headlineMedium,
                color = MaterialTheme.colorScheme.darktext,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
                    .fillMaxWidth(),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = news.source.name,
                        style = Typography.headlineSmall,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(start = MaterialTheme.spacing.small)
                    )

                    Text(
                        text = stringResource(R.string.dot_bullet),
                        style = Typography.headlineSmall,
                        color = Color.Gray,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .padding(horizontal = MaterialTheme.spacing.small)
                    )

                    Text(
                        text = if (news.author.isNullOrEmpty()) stringResource(R.string.unknown) else news.author,
                        style = Typography.headlineSmall,
                        color = Color.Gray,
                        modifier = Modifier
                            .width(40.dp)
                            .basicMarquee(
                                animationMode = MarqueeAnimationMode.Immediately,
                                iterations = Int.MAX_VALUE,
                                spacing = MarqueeSpacing(10.dp),
                                delayMillis = 0,
                                velocity = 20.dp
                            ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                DateChanger.convertDateTime(news.publishedAt)?.let {
                    Text(
                        text = it,
                        style = Typography.headlineSmall,
                        color = Color.Gray,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(end = MaterialTheme.spacing.small)
                    )
                }

            }

        }

    }


}