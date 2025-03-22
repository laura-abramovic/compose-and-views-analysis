package com.abramoviclaura.composeui.screen.bouncingball

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abramoviclaura.composeui.navigation.BottomBarNavDestination
import com.abramoviclaura.shared.R as SharedR

object BouncingBallNavDestination : BottomBarNavDestination {

    private const val ROUTE = "bouncing_ball"

    override val destinationRoute: String = ROUTE
    override val iconRes: Int = SharedR.drawable.ic_basketball_filled
    override val labelRes: Int = SharedR.string.basketball_label

    override fun createDestination(builder: NavGraphBuilder, navController: NavController) {
        builder.composable(destinationRoute) { BouncingBallScreen() }
    }
}
