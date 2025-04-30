package com.apps.histopia.common.bottomNavBar

import android.util.Log
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apps.histopia.ui.theme.BlackBackground
import kotlinx.coroutines.delay


@Composable
fun CustomBottomBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = BlackBackground,
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                )
                .align(Alignment.BottomCenter)
                .fillMaxWidth()

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                BottomBarScreen.items.forEachIndexed { index, screen ->
                    if (index == 1) {
                        CenterFloatingButton(
                            screen = screen,
                            currentRoute = currentRoute,
                            navController = navController
                        )
                    } else {
                        BottomNavItem(
                            screen = screen,
                            currentRoute = currentRoute,
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}


@Composable
private fun BottomNavItem(
    screen: BottomBarScreen,
    currentRoute: String?,
    navController: NavController
) {
    val isActive = currentRoute == screen.route

    Box(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .clickable {
                if (currentRoute != screen.route) {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {
            screen.icon(isActive)
            Text(
                text = screen.title,
                style = MaterialTheme.typography.labelSmall,
                color = if (isActive)
                    Color.White
                else Color.Gray
            )
        }
    }
}

@Composable
private fun CenterFloatingButton(
    screen: BottomBarScreen,
    currentRoute: String?,
    navController: NavController
) {
    val isActive = currentRoute == screen.route
    var isAnimating by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(
        targetValue = if (isAnimating) 0.8f else 1f,
        animationSpec = tween(durationMillis = 200),
        label = "scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .offset(y = (-20).dp)
                .size(56.dp)
                .scale(scale)
                .clip(CircleShape)
                .clickable {
                    isAnimating = true
                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFFA4DCA9),
                            Color(0xFF374D39),
                            Color(0xFF2A482F),
                        ),
                        start = Offset(0f, 0f),
                        end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY)
                    ),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            screen.icon(isActive)
        }
        Text(
            text = screen.title,
            style = MaterialTheme.typography.labelSmall,
            color = if (isActive) Color.White else Color.Gray,
            modifier = Modifier.offset(y = (-16).dp)
        )
    }

    LaunchedEffect(isAnimating) {
        if (isAnimating) {
            delay(200)
            isAnimating = false
        }
    }
}

