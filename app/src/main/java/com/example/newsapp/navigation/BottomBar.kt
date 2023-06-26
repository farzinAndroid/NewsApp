package com.example.newsapp.navigation

import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.newsapp.R
import com.example.newsapp.ui.theme.bottomBar
import com.example.newsapp.ui.theme.darktext
import com.example.newsapp.ui.theme.elevation
import com.example.newsapp.ui.theme.roundedShape
import com.example.newsapp.ui.theme.selectedbottomBar
import com.example.newsapp.ui.theme.spacing
import com.example.newsapp.ui.theme.unselectedbottomBar
import com.exyte.animatednavbar.AnimatedNavigationBar
import com.exyte.animatednavbar.animation.balltrajectory.BallAnimation
import com.exyte.animatednavbar.animation.balltrajectory.Parabolic
import com.exyte.animatednavbar.animation.balltrajectory.Straight
import com.exyte.animatednavbar.animation.balltrajectory.Teleport
import com.exyte.animatednavbar.animation.indendshape.Height
import com.exyte.animatednavbar.animation.indendshape.ShapeCornerRadius
import com.exyte.animatednavbar.animation.indendshape.StraightIndent
import com.exyte.animatednavbar.animation.indendshape.shapeCornerRadius
import com.exyte.animatednavbar.utils.noRippleClickable

@Composable
fun BottomBar(
    navHostController: NavHostController,
    onItemClick: (BottomNavigationItem) -> Unit,
) {

    var selectedIndex by remember {
        mutableStateOf(0)
    }

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

    if (showBottomBar) {

        AnimatedNavigationBar(
            selectedIndex = selectedIndex,
            modifier = Modifier
                .height(64.dp)
                .padding(horizontal = MaterialTheme.spacing.small),
            ballAnimation = Parabolic(tween(500)),
            cornerRadius = shapeCornerRadius(cornerRadius = 34.dp),
            ballColor = if (isSystemInDarkTheme()) Color.White else MaterialTheme.colorScheme.selectedbottomBar,
            barColor = if (isSystemInDarkTheme()) MaterialTheme.colorScheme.bottomBar else MaterialTheme.colorScheme.selectedbottomBar.copy(
                0.5f
            ),
            indentAnimation = Height(tween(300))
        ) {

            bottomNavList.forEachIndexed { index, item ->

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(vertical = MaterialTheme.spacing.small)
                        .noRippleClickable {
                            onItemClick(item)
                            selectedIndex = index
                        }
                ) {

                    Icon(
                        painter = item.icon,
                        contentDescription = item.name,
                        modifier = Modifier
                            .size(24.dp),
                        tint = if (selectedIndex == index) Color.White else Color.LightGray

                    )

                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.headlineSmall,
                        color = if (selectedIndex == index) Color.White else Color.LightGray,
                        modifier = Modifier
                            .padding(top = MaterialTheme.spacing.extraSmall)
                    )

                }


            }
        }
    }


}

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = composed {
    clickable(
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    ) {
        onClick()
    }
}