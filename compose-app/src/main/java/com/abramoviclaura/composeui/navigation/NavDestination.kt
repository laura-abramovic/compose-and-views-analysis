package com.abramoviclaura.composeui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface NavDestination {

    val destinationRoute: String

    fun createDestination(builder: NavGraphBuilder, navController: NavController)
}

interface BottomBarNavDestination : NavDestination {

    @get:DrawableRes
    val iconRes: Int

    @get:StringRes
    val labelRes: Int
}
