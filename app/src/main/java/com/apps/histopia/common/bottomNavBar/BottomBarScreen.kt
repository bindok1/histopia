package com.apps.histopia.common.bottomNavBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.apps.histopia.widget.CustomHomeIcon
import com.apps.histopia.widget.CustomLoveIcon
import com.apps.histopia.widget.CustomProfileIcon

sealed class  BottomBarScreen (
    val route: String,
    val title : String,
    val icon : @Composable (isActive: Boolean)-> Unit
) {
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = {isActive-> CustomHomeIcon(
            isActive = isActive,
        )}
    )

    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = {isActive ->
            CustomProfileIcon(
            activeColor =  Color.White,
            inactiveColor = Color(0xFF9D9D9D),
            isActive = isActive
        ) }
    )
    object  Love: BottomBarScreen(
        route = "love",
        title = "Love",
        icon = {isActive ->
                CustomLoveIcon(
                    activeColor = Color.White,
                    inactiveColor = Color(0xFF9D9D9D),
                    isActive = isActive
                ) }
    )

    companion object{
        val items = listOf(
            Love,
            Home,
            Profile
        )
    }
}