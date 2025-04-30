package com.apps.histopia.features.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.apps.histopia.common.bottomNavBar.CustomBottomBar
import com.apps.histopia.common.navigation.NavigationGraph
import com.apps.histopia.common.utils.NavigationObserver

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    NavigationObserver(navController)
    Scaffold(bottomBar = {
        CustomBottomBar(navController = navController)
    }
    ) { paddingValues ->
       Box (
           modifier = Modifier
               .fillMaxSize()
               .padding(paddingValues)
       ) {
           NavigationGraph(navController = navController)
       }
    }
}