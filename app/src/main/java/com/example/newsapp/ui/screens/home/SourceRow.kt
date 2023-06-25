package com.example.newsapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.ui.component.GlobalNewsAnimation
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SourceRow(
    navController: NavController,
) {



    val sourceList = listOf(
        SourceModel(
            painter = painterResource(R.drawable.bbc),
            text = stringResource(R.string.bbc_news),
            Color(0xFFFC2727),
            id = 1
        ),
        SourceModel(
            painter = painterResource(R.drawable.abc),
            text =stringResource(R.string.abc),
            Color(0xFF000000),
            id = 2
        ),
        SourceModel(
            painter = painterResource(R.drawable.time),
            text = stringResource(R.string.time),
            Color(0xFFFF4800),
            id = 3
        ),
        SourceModel(
            painter = painterResource(R.drawable.asociate_press),
            text = stringResource(R.string.a_p),
            Color(0xFF343535),
            id = 4
        ),
        SourceModel(
            painter = painterResource(R.drawable.bloomberg),
            text = stringResource(R.string.bloomberg),
            Color(0xFFFFA02E),
            id = 5
        ),
        SourceModel(
            painter = painterResource(R.drawable.cnn),
            text = stringResource(R.string.cnn),
            Color(0xFFB9001D),
            id = 6
        ),
        SourceModel(
            painter = painterResource(R.drawable.fox),
            text = stringResource(R.string.fox_news),
            Color(0xFF002D9C),
            id = 7
        ),
        SourceModel(
            painter = painterResource(R.drawable.google),
            text = stringResource(R.string.google),
            Color(0xFF068300),
            id = 8
        )
    )


    var selectedTabIndex by remember {
        mutableStateOf(-1)
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.quick_reads),
            style = Typography.displayLarge,
            color = MaterialTheme.colorScheme.darktext,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
                .padding(top = MaterialTheme.spacing.medium)
                .padding(bottom = MaterialTheme.spacing.medium)
        )


        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = Color.Transparent,
                divider = {
                    Divider(
                        color = Color.Transparent
                    )
                },
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        color = Color.Transparent
                    )
                },
                edgePadding = MaterialTheme.spacing.semiSmall
            ) {

                sourceList.forEachIndexed { index, sourceModel ->
                    Tab(
                        modifier = Modifier
                            .height(130.dp),
                        selected = (selectedTabIndex == index),
                        onClick = { selectedTabIndex = index },
                        selectedContentColor = sourceModel.color,
                        content = {
                            SourceItem(
                                index = index,
                                selectedTabPosition = selectedTabIndex,
                                item = sourceModel
                            ) {
                                selectedTabIndex = index
                            }
                        }
                    )

                }
            }

            when (selectedTabIndex) {
                0 -> {
                    TopNewsSection("bbc-news")
                }

                1 -> {
                    TopNewsSection("abc-news")
                }

                2 -> {
                    TopNewsSection("time")
                }

                3 -> {
                    TopNewsSection("associated-press")
                }

                4 -> {
                    TopNewsSection("bloomberg")
                }

                5 -> {
                    TopNewsSection("cnn")
                }

                6 -> {
                    TopNewsSection("fox-news")
                }

                7 -> {
                    TopNewsSection("google-news")
                }
                else->{
                    GlobalNewsAnimation()
                }
            }
        }
    }


}