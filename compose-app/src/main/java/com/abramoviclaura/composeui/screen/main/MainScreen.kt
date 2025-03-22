package com.abramoviclaura.composeui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.abramoviclaura.composeui.screen.bouncingball.BouncingBallNavDestination
import com.abramoviclaura.composeui.screen.details.DetailsNavDestination
import com.abramoviclaura.composeui.screen.list.ListNavDestination
import com.abramoviclaura.composeui.screen.main.components.BottomNavigationBar
import com.abramoviclaura.composeui.screen.main.components.toNavDestinationItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomItems = listOf(
        ListNavDestination.toNavDestinationItem(navController),
        BouncingBallNavDestination.toNavDestinationItem(navController)
    )

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding(),
        bottomBar = {
            BottomNavigationBar(bottomItems)
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = ListNavDestination.destinationRoute,
            modifier = Modifier.padding(paddingValues)
        ) {
            ListNavDestination.createDestination(this, navController)
            BouncingBallNavDestination.createDestination(this, navController)
            DetailsNavDestination.createDestination(this, navController)
        }
    }
}
