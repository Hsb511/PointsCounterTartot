package com.team23.ui.themes

import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.ui.graphics.Color

val Turquoise = Color(0xFF1EDDCD)
val Olivine = Color(0xFFADC178)
val IndianRed = Color(0xFFE15A58)
val MiddleRedPurple = Color(0xFF994B47)
val SandyBrown = Color(0xFFF4A259)
val GoldCrayola = Color(0xFFEDC298)
val CharCoal = Color(0xFF3E4C59)

val PointsCounterTartotLight = lightColors(
    primary = MiddleRedPurple,
    primaryVariant = IndianRed,
    secondary = SandyBrown,
    secondaryVariant = Olivine,
    onPrimary = Color.White
)

val PointsCounterTartotDark = darkColors(
    primary = GoldCrayola,
    primaryVariant = SandyBrown,
    secondary = Turquoise,
    secondaryVariant = CharCoal,
    onPrimary = Color.Black
)