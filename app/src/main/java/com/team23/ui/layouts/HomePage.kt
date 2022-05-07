package com.team23.ui.layouts

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.R
import com.team23.domain.enums.GameTypeEnum
import com.team23.domain.models.Game
import com.team23.domain.models.Player
import com.team23.ui.components.HomeCard
import com.team23.ui.viewmodels.HomePageViewModel

@Composable
fun HomePage(homePageViewModel: HomePageViewModel = viewModel(), navController: NavHostController) {
    HomePage(
        tarotGames = homePageViewModel.tarotGames
    )
}

@Composable
fun HomePage(
    tarotGames: List<Game>
) {
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {

        when (configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                HomeCard(
                    title = stringResource(id = R.string.home_tarot),
                    modifier = Modifier.weight(1f),
                    games = tarotGames
                ) { }
                HomeCard(
                    title = stringResource(id = R.string.home_whist),
                    modifier = Modifier.weight(1f),
                    games = emptyList()
                ) { }
                HomeCard(
                    title = stringResource(id = R.string.home_coinche),
                    modifier = Modifier.weight(1f),
                    games = emptyList()
                ) { }
                HomeCard(
                    title = stringResource(id = R.string.home_belote),
                    modifier = Modifier.weight(1f),
                    games = emptyList()
                ) { }
            }
            else -> {
                Row(modifier = Modifier.weight(1f)) {
                    HomeCard(
                        title = stringResource(id = R.string.home_tarot),
                        modifier = Modifier.weight(1f),
                        games = tarotGames
                    ) { }
                    HomeCard(
                        title = stringResource(id = R.string.home_whist),
                        modifier = Modifier.weight(1f),
                        games = emptyList()
                    ) { }
                }
                Row(modifier = Modifier.weight(1f)) {
                    HomeCard(
                        title = stringResource(id = R.string.home_coinche),
                        modifier = Modifier.weight(1f),
                        games = emptyList()
                    ) { }
                    HomeCard(
                        title = stringResource(id = R.string.home_belote),
                        modifier = Modifier.weight(1f),
                        games = emptyList()
                    ) { }
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
fun HomePagePreview() {
    HomePage(
        tarotGames = listOf(
            Game(
                id = 0,
                gameType = GameTypeEnum.FRENCH_TAROT,
                players = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
                    .mapIndexed { index, value -> Player(index, value) }
            )
        )
    )
}