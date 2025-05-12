package com.apps.histopia.common.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.apps.histopia.common.bottomNavBar.BottomBarScreen
import com.apps.histopia.architecture.features.page.Home.HomeScreen
import com.apps.histopia.architecture.features.page.LoveScreen
import com.apps.histopia.architecture.features.page.ProfileScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route,
        modifier = Modifier
    ) {
        composable(BottomBarScreen.Home.route) {
            HomeScreen()
        }
        composable(BottomBarScreen.Profile.route) {
            ProfileScreen()
        }
        composable(BottomBarScreen.Love.route) {
            LoveScreen()
        }
    }
}