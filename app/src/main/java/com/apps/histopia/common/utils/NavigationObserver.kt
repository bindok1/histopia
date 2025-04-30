package com.apps.histopia.common.utils

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect

import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun NavigationObserver(navController: NavController) {
    DisposableEffect(navController) {
        val listener =
            NavController.OnDestinationChangedListener { controller, destination, arguments ->
                Log.d("Navigation", "Current route: ${destination.route}")
                Log.d("Navigation", "Arguments: $arguments")

                val previousEntry = controller.previousBackStackEntry
                Log.d("Navigation ⭐", "Previous route: ${previousEntry?.destination?.route}")

            }

        navController.addOnDestinationChangedListener(listener)
        onDispose {
            navController.removeOnDestinationChangedListener(listener)
        }

    }
}