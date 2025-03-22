package com.abramoviclaura.composeui.screen.greetings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abramoviclaura.composeui.navigation.BottomBarNavDestination
import com.abramoviclaura.shared.R as SharedR

object GreetingsNavDestination : BottomBarNavDestination {

    private const val ROUTE = "greetings"

    override val destinationRoute: String = ROUTE
    override val iconRes: Int = SharedR.drawable.ic_waving_hand
    override val labelRes: Int = SharedR.string.greetings_label

    override fun createDestination(builder: NavGraphBuilder, navController: NavController) {
        builder.composable(destinationRoute) { GreetingsScreen() }
    }
}
