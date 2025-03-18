package com.abramoviclaura.composeui.screen.main.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.abramoviclaura.composeui.ui.theme.Black
import com.abramoviclaura.composeui.ui.theme.LightGray
import com.abramoviclaura.composeui.ui.theme.LightPink

@Composable
fun BottomNavigationBar(items: List<NavDestinationItem>) {
    NavigationBar(containerColor = LightPink) {
        items.forEach { item ->
            NavigationBarItem(
                selected = item.isSelected,
                onClick = item.onClick,
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconRes),
                        contentDescription = null
                    )
                },
                label = {
                    Text(text = stringResource(item.labelRes))
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Black,
                    unselectedIconColor = LightGray,
                    selectedTextColor = Black,
                    unselectedTextColor = LightGray,
                    indicatorColor = Color.Transparent,
                )
            )
        }
    }
}
