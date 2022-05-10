package com.team23.ui.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable


@Composable
fun PointsCounterTartotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val pointsCounterTartotColors = if (darkTheme) {
        PointsCounterTartotDark
    } else {
        PointsCounterTartotLight
    }

    MaterialTheme(
        colors = pointsCounterTartotColors,
        typography = Typography(),
        shapes = PointsCounterTartotShapes,
        content = content
    )
}