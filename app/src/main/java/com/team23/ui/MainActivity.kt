package com.team23.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.team23.ui.layouts.MainLayout
import com.team23.ui.themes.PointsCounterTartotTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { PointsCounterTartotTheme { MainLayout() } }
    }
}