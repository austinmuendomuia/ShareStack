package com.sharestack.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val ShareStackColorScheme = lightColorScheme(
    background = StoneBackground,
    surface = StoneSurface,
    primary = TextPrimary,
    secondary = TextSecondary,
    tertiary = AccentGreen,
    error = AccentRed
)

@Composable
fun ShareStackTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = ShareStackColorScheme,
        content = content
    )
}