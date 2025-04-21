package com.apps.starterkotlin.ui.theme




import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.apps.starterkotlin.R


private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val PlusJakartaSansFont = GoogleFont("Plus Jakarta Sans")

val PlusJakartaSans = FontFamily(
    Font(
        googleFont = PlusJakartaSansFont,
        fontProvider = provider,
        weight = FontWeight.Light
    ),
    Font(
        googleFont = PlusJakartaSansFont,
        fontProvider = provider,
        weight = FontWeight.Normal
    ),
    Font(
        googleFont = PlusJakartaSansFont,
        fontProvider = provider,
        weight = FontWeight.Medium
    ),
    Font(
        googleFont = PlusJakartaSansFont,
        fontProvider = provider,
        weight = FontWeight.SemiBold
    ),
    Font(
        googleFont = PlusJakartaSansFont,
        fontProvider = provider,
        weight = FontWeight.Bold
    ),
)