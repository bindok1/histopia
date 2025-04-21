package com.apps.starterkotlin.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


val TextTypography = Typography(
    // Display styles
    displayLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 57.sp,
        lineHeight = 64.sp,
        fontWeight = FontWeight.Normal
    ),
    displayMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 45.sp,
        lineHeight = 52.sp,
        fontWeight = FontWeight.Normal
    ),

    // Headline styles
    headlineLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 32.sp,
        lineHeight = 40.sp,
        fontWeight = FontWeight.Medium
    ),
    headlineMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 28.sp,
        lineHeight = 36.sp,
        fontWeight = FontWeight.Medium
    ),

    // Title styles
    titleLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight.SemiBold
    ),
    titleMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.15.sp
    ),

    // Body styles
    bodyLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Normal
    ),

    // Label styles
    labelLarge = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.1.sp
    ),
    labelSmall = TextStyle(
        fontFamily = PlusJakartaSans,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Medium,
        letterSpacing = 0.5.sp
    )
)
