package com.apps.starterkotlin.common.routers

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apps.starterkotlin.features.alpha_features.page.HomeScreen

object AppRoute {
    const val HOME = "home"
    const val PROFILE = "profile"
}

// Navigation.kt
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    startDestination: String = AppRoute.HOME
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppRoute.HOME) {
            HomeScreen(
                onNavigateToProfile = {
                    navController.navigate(AppRoute.PROFILE)
                }
            )
        }
    }
}
