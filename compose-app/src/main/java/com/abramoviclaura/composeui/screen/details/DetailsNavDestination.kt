package com.abramoviclaura.composeui.screen.details

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.abramoviclaura.composeui.navigation.NavDestination

object DetailsNavDestination : NavDestination {

    private const val ARGUMENT_ID = "id"
    private const val ROUTE = "list/{$ARGUMENT_ID}"

    override val destinationRoute: String = ROUTE

    override fun createDestination(builder: NavGraphBuilder, navController: NavController) {
        builder.composable(
            destinationRoute,
            arguments = listOf(navArgument(ARGUMENT_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(ARGUMENT_ID)

            itemId?.let {
                DetailsScreen(
                    itemId = itemId,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }

    fun navigateTo(id: Int, navController: NavController) {
        val navigationRoute = ROUTE.replace("{$ARGUMENT_ID}", id.toString())
        navController.navigate(navigationRoute)
    }
}
