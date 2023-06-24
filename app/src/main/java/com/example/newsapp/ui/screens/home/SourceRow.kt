package com.example.newsapp.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.TabPosition
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.bottomBar
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SourceRow(
    navController: NavController,
) {

    var selectedItem = remember { mutableStateOf<SourceModel?>(null) }


    val sourceList = listOf(
        SourceModel(
            painter = painterResource(R.drawable.bbc),
            text = "BBC News",
            Color(0xFFFC2727),
            id = 1
        ),
        SourceModel(
            painter = painterResource(R.drawable.abc),
            text = "ABC",
            Color(0xFF000000),
            id = 2
        ),
        SourceModel(
            painter = painterResource(R.drawable.time),
            text = "Time",
            Color(0xFFFF4800),
            id = 3
        ),
        SourceModel(
            painter = painterResource(R.drawable.asociate_press),
            text = "Associated Press",
            Color(0xFF343535),
            id = 4
        ),
        SourceModel(
            painter = painterResource(R.drawable.bloomberg),
            text = "Bloomberg",
            Color(0xFFFFA02E),
            id = 5
        ),
        SourceModel(
            painter = painterResource(R.drawable.cnn),
            text = "Cnn",
            Color(0xFFB9001D),
            id = 6
        ),
        SourceModel(
            painter = painterResource(R.drawable.fox),
            text = "Fox News",
            Color(0xFF002D9C),
            id = 7
        ),
        SourceModel(
            painter = painterResource(R.drawable.google),
            text = "Google",
            Color(0xFF068300),
            id = 8
        )
    )


    var selectedTabIndex by remember {
        mutableStateOf(0)
    }



    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Text(
            text = stringResource(R.string.quick_reads),
            style = Typography.displayLarge,
            color = MaterialTheme.colorScheme.darktext,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(start = MaterialTheme.spacing.medium)
                .padding(top = MaterialTheme.spacing.medium)
                .padding(bottom = MaterialTheme.spacing.medium)
        )


        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {
            ScrollableTabRow(
                selectedTabIndex = selectedTabIndex,
                containerColor = MaterialTheme.colorScheme.bottomBar,
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
                    /*when(selectedItem.value){
                        is SourceModel ->{
                            val selectedSource = selectedItem.value as SourceModel

            //                loading = true

                            when(selectedSource.id){



                                1 ->{
                                   TopNewsSection(source = "bbc-news")
                                }

                                2 ->{
                                    TopNewsSection(source = "abc-news")
                                }

                                3 ->{
                                    TopNewsSection(source = "time")
                                }

                                4 ->{
                                    TopNewsSection(source = "associated-press")
                                }

                                5 ->{
                                    TopNewsSection(source = "bloomberg")
                                }

                                6 ->{
                                    TopNewsSection(source = "cnn")
                                }

                                7 ->{
                                    TopNewsSection(source = "fox-news")
                                }

                                8 ->{
                                    TopNewsSection(source = "google-news")
                                }



                            }
            //                loading = false

                        }
                    }*/
                    Text("google-news")
                }
            }
        }


        /*LazyRow(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            itemsIndexed(sourceList) { index, source ->
                SourceItem(source){
                    selectedItem.value = source
                }
            }
        }*/




    }


}