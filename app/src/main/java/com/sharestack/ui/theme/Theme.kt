package com.sharestack.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
private val ShareStackColorScheme = lightColorScheme(
    // The main branding colors
    primary = BrandPrimary,
    onPrimary = Color.White,

    // This makes your big "Total Vault" cards a solid, beautiful block of color
    primaryContainer = BrandPrimary,
    onPrimaryContainer = Color.White,

    // This makes secondary cards (like the hub card) a soft, tinted indigo
    secondaryContainer = Color(0xFFEEF2FF),
    onSecondaryContainer = BrandPrimaryDark,

    // Backgrounds and text
    background = AppBackground,
    onBackground = TextPrimary,
    surface = CardSurface,
    onSurface = TextPrimary,

    // Accents
    tertiary = SuccessGreen,
    error = AlertRed
)

@Composable
fun ShareStackTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ShareStackColorScheme,
        content = content
    )
}