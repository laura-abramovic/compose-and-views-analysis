package com.abramoviclaura.composeui.screen.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abramoviclaura.composeui.navigation.NavDestination
import com.abramoviclaura.shared.R as SharedR

object ListNavDestination : NavDestination {

    private const val ROUTE = "list"

    override val destinationRoute: String = ROUTE
    override val iconRes: Int = SharedR.drawable.ic_list
    override val labelRes: Int = SharedR.string.list_label

    override fun createDestination(builder: NavGraphBuilder) {
        builder.composable(destinationRoute) { ListScreen() }
    }
}
