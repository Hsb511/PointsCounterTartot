package com.team23.ui.themes

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Olivine = Color(173, 193, 120)
val MossGreen = Color(130, 145, 90)
val SandyBrown = Color(244, 162, 89)

val PointsCounterTartotLight = lightColors(
    primary = Olivine,
    primaryVariant = MossGreen,
    secondary = SandyBrown,
    onPrimary = Color.Black
)

val PointsCounterTartotDark = darkColors(
    primary = Olivine,
    primaryVariant = MossGreen,
    secondary = SandyBrown
)