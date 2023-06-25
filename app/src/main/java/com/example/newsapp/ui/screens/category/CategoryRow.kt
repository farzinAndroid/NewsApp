package com.example.newsapp.ui.screens.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
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
import com.example.newsapp.ui.theme.selectedbottomBar
import com.example.newsapp.ui.theme.spacing

@Composable
fun CategoryRow(navController: NavController) {


    val categoryList = listOf(
        CategoryModel(
            text = stringResource(R.string.general),
            painter = painterResource(R.drawable.general)
        ),
        CategoryModel(
            text = stringResource(R.string.sport),
            painter = painterResource(R.drawable.sport)
        ),
        CategoryModel(
            text = stringResource(R.string.politics),
            painter = painterResource(R.drawable.politics)
        ),
        CategoryModel(
            text = stringResource(R.string.business),
            painter = painterResource(R.drawable.business)
        ),
        CategoryModel(
            text = stringResource(R.string.health),
            painter = painterResource(R.drawable.heakth)
        ),
        CategoryModel(
            text = stringResource(R.string.technology),
            painter = painterResource(R.drawable.technology)
        ),
        CategoryModel(
            text = stringResource(R.string.science),
            painter = painterResource(R.drawable.science)
        ),
        CategoryModel(
            text = stringResource(R.string.entertainment),
            painter = painterResource(R.drawable.entertainment)
        ),
    )

    var selectedTabIndex by remember {
        mutableStateOf(-1)
    }


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Text(
            text = stringResource(R.string.all_Your_interests___),
            style = Typography.displayLarge,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .width(200.dp)
                .padding(start = MaterialTheme.spacing.medium)
                .padding(top = MaterialTheme.spacing.medium)
                .padding(bottom = MaterialTheme.spacing.medium),
            maxLines = 2,
            color = MaterialTheme.colorScheme.darktext
        )
    }



    Column {
        androidx.compose.material3.ScrollableTabRow(
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

            categoryList.forEachIndexed { index, categoryModel ->

                Tab(
                    modifier = Modifier
                        .height(130.dp),
                    selected = (selectedTabIndex == index),
                    onClick = { selectedTabIndex = index },
                    selectedContentColor = MaterialTheme.colorScheme.selectedbottomBar,
                    content = {
                        CategoryItem(
                            index = index,
                            selectedTabPosition = selectedTabIndex,
                            item = categoryModel
                        ) {
                            selectedTabIndex = index
                        }
                    }
                )

            }

        }

        when (selectedTabIndex) {
            0 -> {
                NewsBasedOnCategorySection("general",navController)
            }

            1 -> {
                NewsBasedOnCategorySection("sport",navController)
            }

            2 -> {
                NewsBasedOnCategorySection("politics",navController)
            }

            3 -> {
                NewsBasedOnCategorySection("business",navController)
            }

            4 -> {
                NewsBasedOnCategorySection("health",navController)
            }

            5 -> {
                NewsBasedOnCategorySection("technology",navController)
            }

            6 -> {
                NewsBasedOnCategorySection("science",navController)
            }

            7 -> {
                NewsBasedOnCategorySection("entertainment",navController)
            }
            else->{
                GlobalNewsAnimation()
            }
        }

    }


}