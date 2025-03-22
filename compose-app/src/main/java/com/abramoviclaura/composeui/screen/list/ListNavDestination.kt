package com.abramoviclaura.composeui.screen.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.abramoviclaura.composeui.navigation.BottomBarNavDestination
import com.abramoviclaura.composeui.screen.details.DetailsNavDestination
import com.abramoviclaura.shared.R as SharedR

object ListNavDestination : BottomBarNavDestination {

    private const val ROUTE = "list"

    override val destinationRoute: String = ROUTE
    override val iconRes: Int = SharedR.drawable.ic_list
    override val labelRes: Int = SharedR.string.list_label

    override fun createDestination(builder: NavGraphBuilder, navController: NavController) {
        builder.composable(destinationRoute) {
            ListScreen(
                onItemClick = {
                    DetailsNavDestination.navigateTo(it, navController)
                }
            )
        }
    }
}
