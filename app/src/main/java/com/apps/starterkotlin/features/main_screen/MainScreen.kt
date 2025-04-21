package com.apps.starterkotlin.features.main_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apps.starterkotlin.common.bottomNavBar.BottomBar
import com.apps.starterkotlin.common.bottomNavBar.BottomBarScreen
import com.apps.starterkotlin.features.home.page.HomeScreen
import com.apps.starterkotlin.features.profile.page.ProfileScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(bottomBar = {
        BottomBar(navController = navController)
    }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomBarScreen.Home.route) {
                HomeScreen()
            }
            composable(BottomBarScreen.Profile.route) {
                ProfileScreen()
            }
        }
    }
}