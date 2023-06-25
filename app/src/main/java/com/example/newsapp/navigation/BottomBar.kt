package com.example.newsapp.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.R
import com.example.newsapp.ui.theme.bottomBar
import com.example.newsapp.ui.theme.elevation
import com.example.newsapp.ui.theme.roundedShape
import com.example.newsapp.ui.theme.selectedbottomBar
import com.example.newsapp.ui.theme.spacing
import com.example.newsapp.ui.theme.unselectedbottomBar

@Composable
fun BottomBar(
    navHostController: NavHostController,
    onItemClick: (BottomNavigationItem) -> Unit,
) {

    val bottomNavList = listOf(
        BottomNavigationItem(
            name = stringResource(R.string.home),
            icon = painterResource(R.drawable.home),
            route = Screens.HomeScreen.route
        ),

        BottomNavigationItem(
            name = stringResource(R.string.category),
            icon = painterResource(R.drawable.category),
            route = Screens.CategoryScreen.route
        ),

        BottomNavigationItem(
            name = stringResource(R.string.search),
            icon = painterResource(R.drawable.search_1),
            route = Screens.SearchScreen.route
        )
    )

    val backStackEntry = navHostController.currentBackStackEntryAsState()
    val showBottomBar = backStackEntry.value?.destination?.route in bottomNavList.map { it.route }

    if (showBottomBar){
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.bottomBar,
            modifier = Modifier
                .height(60.dp)
                .clip(MaterialTheme.roundedShape.small)
            ,
            tonalElevation = MaterialTheme.elevation.extraSmall,
        ) {

            bottomNavList.forEachIndexed { index, item ->

                val selected = item.route == backStackEntry.value?.destination?.route

                BottomNavigationItem(
                    selected = selected,
                    onClick = {
                        onItemClick(item)
                    },
                    selectedContentColor = MaterialTheme.colorScheme.selectedbottomBar,
                    unselectedContentColor = MaterialTheme.colorScheme.unselectedbottomBar,
                    icon = {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                painter = item.icon,
                                contentDescription = item.name,
                                modifier = Modifier
                                    .size(24.dp)

                            )

                            Text(
                                text = item.name,
                                style = MaterialTheme.typography.headlineSmall,
                                color = if (selected) MaterialTheme.colorScheme.selectedbottomBar else MaterialTheme.colorScheme.unselectedbottomBar,
                                modifier = Modifier
                                    .padding(top = MaterialTheme.spacing.extraSmall)
                            )

                        }
                    },
                )

            }

        }
    }


}