package com.team23.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.R
import com.team23.ui.components.HomeCard
import com.team23.ui.viewmodels.HomePageViewModel

@Composable
fun HomePage(homePageViewModel: HomePageViewModel = viewModel(), navController: NavHostController) {
    HomePage()
}

@Composable
fun HomePage() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Row(modifier = Modifier.weight(1f)) {
            HomeCard(
                title = stringResource(id = R.string.home_tarot),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "content")
            }
            HomeCard(
                title = stringResource(id = R.string.home_whist),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "content")
            }
        }
        Row(modifier = Modifier.weight(1f)) {
            HomeCard(
                title = stringResource(id = R.string.home_coinche),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "content")
            }
            HomeCard(
                title = stringResource(id = R.string.home_belote),
                modifier = Modifier.weight(1f)
            ) {
                Text(text = "content")
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun HomePagePreview() {
    HomePage()
}