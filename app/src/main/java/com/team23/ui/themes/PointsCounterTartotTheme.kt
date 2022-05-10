package com.team23.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors()
private val LightColorPalette = lightColors()

@Composable
fun PointsCounterTartotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) { DarkColorPalette } else { LightColorPalette }

    MaterialTheme(colors = colors, typography = Typography(), shapes = PointsCounterTartotShapes, content = content)
}