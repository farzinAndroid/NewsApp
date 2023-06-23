package com.example.newsapp.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.newsapp.R
import com.example.newsapp.ui.theme.Typography
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.spacing

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SourceRow(
    navController: NavController,
) {

    val selectedItem = remember { mutableStateOf<SourceModel?>(null) }

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

        LazyRow(
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
        }


        when(selectedItem.value){
            is SourceModel ->{
                val selectedSource = selectedItem.value as SourceModel

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

            }
        }



//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(120.dp)
//                .horizontalScroll(rememberScrollState()),
//            horizontalArrangement = Arrangement.Center,
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//
//
//            sourceList.forEachIndexed { index, sourceModel ->
//                SourceItem(sourceModel)
//            }
//
//            for (items in sourceList){
//                SourceItem(items)
//            }
//
//
//        }
    }


}