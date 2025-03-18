package com.abramoviclaura.composeui.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder

interface NavDestination {
    val destinationRoute: String

    @get:DrawableRes
    val iconRes: Int

    @get:StringRes
    val labelRes: Int

    fun createDestination(builder: NavGraphBuilder)
}
