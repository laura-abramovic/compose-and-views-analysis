package com.abramoviclaura.composeui.screen.main.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.abramoviclaura.composeui.navigation.BottomBarNavDestination

data class NavDestinationItem(
    @DrawableRes val iconRes: Int,
    @StringRes val labelRes: Int,
    val onClick: () -> Unit,
    val isSelected: Boolean,
)

@Composable
fun BottomBarNavDestination.toNavDestinationItem(navController: NavController): NavDestinationItem {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentHierarchy = navBackStackEntry?.destination?.hierarchy

    return NavDestinationItem(
        iconRes = iconRes,
        labelRes = labelRes,
        onClick = {
            navController.navigate(destinationRoute) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }

                restoreState = true
                launchSingleTop = true
            }
        },
        isSelected = currentHierarchy?.any { it.route == destinationRoute } == true
    )
}
