package com.team23.ui.layouts

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainLayout() {
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = "tarot") {
            composable(route = "tarot") { TarotScreen(hiltViewModel(), navController) }
            composable(route = "tarotForm") { TarotForm(hiltViewModel(), navController) }
        }
    }
}