package com.apps.histopia.architecture.features.main_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.apps.histopia.common.bottomNavBar.CustomBottomBar
import com.apps.histopia.common.navigation.NavigationGraph
import com.apps.histopia.common.utils.NavigationObserver
import com.google.accompanist.navigation.material.BottomSheetNavigator
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator


@OptIn(ExperimentalMaterialNavigationApi::class)
@Composable
fun MainScreen() {
    val bottomSheetNavigator = rememberBottomSheetNavigator()
    val navController = rememberNavController(
        navigators = arrayOf(bottomSheetNavigator)
    )
    NavigationObserver(navController)


    ModalBottomSheetLayout(
        bottomSheetNavigator = bottomSheetNavigator
    ) {
        Scaffold(bottomBar = {
            CustomBottomBar(navController = navController)
        }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                NavigationGraph(navController = navController)
            }
        }
    }
}