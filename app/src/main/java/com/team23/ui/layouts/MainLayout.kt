package com.team23.ui.layouts

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team23.ui.viewmodels.HomePageViewModel
import com.team23.ui.viewmodels.TarotViewModel

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun MainLayout() {
    val tarotViewModel: TarotViewModel = hiltViewModel()
    val homePageViewModel: HomePageViewModel = hiltViewModel()
    val navController = rememberNavController()

    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(navController, startDestination = "home") {
            composable(route = "home") { HomePage(homePageViewModel, navController) }
            composable(route = "tarot/{gameId}") { backStackEntry ->
                TarotScreen(
                    tarotViewModel,
                    navController,
                    backStackEntry.arguments?.getString("gameId" )
                )
            }
            composable(route = "tarotForm") { TarotForm(tarotViewModel, navController) }
        }
    }
}