package com.abramoviclaura.composeui.screen.main

import android.content.Context
import android.content.ContextWrapper
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.abramoviclaura.composeui.screen.bouncingball.BouncingBallNavDestination
import com.abramoviclaura.composeui.screen.details.DetailsNavDestination
import com.abramoviclaura.composeui.screen.greetings.GreetingsNavDestination
import com.abramoviclaura.composeui.screen.list.ListNavDestination
import com.abramoviclaura.composeui.screen.main.components.BottomNavigationBar
import com.abramoviclaura.composeui.screen.main.components.toNavDestinationItem

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val bottomItems = listOf(
        ListNavDestination.toNavDestinationItem(navController),
        BouncingBallNavDestination.toNavDestinationItem(navController),
        GreetingsNavDestination.toNavDestinationItem(navController),
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
            DetailsNavDestination.createDestination(this, navController)
            BouncingBallNavDestination.createDestination(this, navController)
            GreetingsNavDestination.createDestination(this, navController)
        }
    }
}

fun Context.getActivity(): ComponentActivity? = when (this) {
    is ComponentActivity -> this
    is ContextWrapper -> baseContext.getActivity()
    else -> null
}
