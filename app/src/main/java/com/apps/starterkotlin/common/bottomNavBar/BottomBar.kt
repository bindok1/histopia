package com.apps.starterkotlin.common.bottomNavBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState


@Composable
fun BottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val screens = listOf(
    BottomBarScreen.Home to BottomBarScreen.Home.icon,
    BottomBarScreen.Profile to BottomBarScreen.Profile.icon
    )

    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute= navBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier,
    ) {
        screens.forEach { (route, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = route.route) },
                label = { Text(route.route.replaceFirstChar { it.uppercase() }) },
                selected = currentRoute == route.route,
                onClick = {
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
