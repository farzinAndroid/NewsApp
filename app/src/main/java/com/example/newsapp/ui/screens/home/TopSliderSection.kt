package com.example.newsapp.ui.screens.home

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.MarqueeSpacing
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.newsapp.R
import com.example.newsapp.data.internet.NetworkResult
import com.example.newsapp.data.model.home.TopNewsModel
import com.example.newsapp.ui.theme.LocalSpacing
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.roundedShape
import com.example.newsapp.ui.theme.spacing
import com.example.newsapp.viewmodel.HomeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter

@OptIn(ExperimentalPagerApi::class, ExperimentalFoundationApi::class)
@Composable
fun TopSliderSection(
    homeViewModel: HomeViewModel = hiltViewModel(),
) {


    var topNewsList by remember {
        mutableStateOf<List<TopNewsModel>>(emptyList())
    }

    var loading by remember {
        mutableStateOf(false)
    }



    val pagerState = rememberPagerState()


    val topNewsSliderResult by homeViewModel.topHeadline.collectAsState()
    when (topNewsSliderResult) {
        is NetworkResult.Success -> {

            topNewsList = topNewsSliderResult.data ?: emptyList()
            loading = false

        }

        is NetworkResult.Error -> {
            Log.e("TAG", "topNewsSliderResultError ${topNewsSliderResult.message}")
            loading = false
        }

        is NetworkResult.Loading -> {
            loading = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(
                    horizontal = MaterialTheme.spacing.extraSmall,
                    vertical = MaterialTheme.spacing.small
                )
        ) {

            var imageUrl by remember { mutableStateOf("") }

            HorizontalPager(
                count = topNewsList.size,
                state = pagerState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()

            ) { index ->

                imageUrl = topNewsList[index].urlToImage

                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter
                ) {
                    Image(
                        painter = if (imageUrl.isNullOrEmpty()) painterResource(R.drawable.cnn) else rememberAsyncImagePainter(
                            imageUrl
                        ),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(LocalSpacing.current.small)
                            .clip(MaterialTheme.roundedShape.medium)
                            .fillMaxSize()
                            .clickable {  },
                        contentScale = ContentScale.FillBounds
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(LocalSpacing.current.small)
                            .clip(MaterialTheme.roundedShape.medium)
                            .background(
                                brush = Brush.verticalGradient(
                                    listOf(
                                        Color.Transparent,
                                        Color.Transparent,
                                        Color.Transparent,
                                        Color.Black,
                                    )
                                )
                            ),
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.BottomStart
                    ) {

                        Text(
                            text = if (imageUrl.isNullOrEmpty()) "" else topNewsList[index].title,
                            style = Typography.displayLarge,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MaterialTheme.spacing.medium)
                                .padding(bottom = MaterialTheme.spacing.semiLarge)
                                .basicMarquee(
                                    animationMode = MarqueeAnimationMode.Immediately,
                                    iterations = Int.MAX_VALUE,
                                    spacing = MarqueeSpacing(10.dp),
                                    delayMillis = 0,
                                    velocity = 50.dp
                                )
                        )

                    }
                }

            }

            val isDraggedState by pagerState.interactionSource.collectIsDraggedAsState()
            LaunchedEffect(isDraggedState) {
                snapshotFlow { isDraggedState }
                    .filter { !isDraggedState }
                    .collectLatest { isDragged ->
                        while (true) {
                            delay(6000)
                            var newPosition = pagerState.currentPage + 1
                            if (newPosition > topNewsList.size - 1) newPosition = 0
                            pagerState.animateScrollToPage(newPosition)
                        }


                    }
            }


        }


    }
}