package com.team23.ui.layouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.ui.viewmodels.HomePageViewModel

@Composable
fun HomePage(homePageViewModel: HomePageViewModel = viewModel(), navController: NavHostController) {
    HomePage()
}

@Composable
fun HomePage() {

}

@Preview(showSystemUi = true)
@Composable
fun HomePagePreview() {
    HomePage()
}