package com.apps.histopia.ui.theme




import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.text.googlefonts.Font
import com.apps.histopia.R


private val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

private val DMSerifDisplayFont = GoogleFont("DM Serif Display")

val DMSerifDisplay = FontFamily(
    Font(
        googleFont = DMSerifDisplayFont,
        fontProvider = provider,
        weight = FontWeight.Light
    ),
    Font(
        googleFont = DMSerifDisplayFont,
        fontProvider = provider,
        weight = FontWeight.Normal
    ),
    Font(
        googleFont = DMSerifDisplayFont,
        fontProvider = provider,
        weight = FontWeight.Medium
    ),
    Font(
        googleFont = DMSerifDisplayFont,
        fontProvider = provider,
        weight = FontWeight.SemiBold
    ),
    Font(
        googleFont = DMSerifDisplayFont,
        fontProvider = provider,
        weight = FontWeight.Bold
    ),
)


private val DMSansFont = GoogleFont("DM Sans")

val DMSans = FontFamily(
    Font(
        googleFont = DMSansFont,
        fontProvider = provider,
        weight = FontWeight.Light
    ),
    Font(
        googleFont = DMSansFont,
        fontProvider = provider,
        weight = FontWeight.Normal
    ),
    Font(
        googleFont = DMSansFont,
        fontProvider = provider,
        weight = FontWeight.Medium
    ),
    Font(
        googleFont = DMSansFont,
        fontProvider = provider,
        weight = FontWeight.SemiBold
    ),
    Font(
        googleFont = DMSansFont,
        fontProvider = provider,
        weight = FontWeight.Bold
    )
)
