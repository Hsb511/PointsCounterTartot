package com.team23.ui.layouts

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
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

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun HomePage(homePageViewModel: HomePageViewModel = viewModel(), navController: NavHostController) {
    homePageViewModel.refreshTarotGames()
    HomePage(
        tarotGames = homePageViewModel.tarotGames,
        onNewTarotGame = {
            homePageViewModel.createNewTarotGame()
            navController.navigate("tarot")
        },
        onOldTarotGame = { gameId ->
            navController.navigate("tarot/$gameId")
        }
    )
}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun HomePage(
    tarotGames: List<Game>,
    onNewTarotGame: () -> Unit,
    onOldTarotGame: (Int) -> Unit
) {
    val configuration = LocalConfiguration.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {
        @Composable
        fun tarotHomeCard() = HomeCard(
            title = stringResource(id = R.string.home_tarot),
            modifier = Modifier.weight(1f),
            games = tarotGames,
            onAddNewGame = { onNewTarotGame() },
            onNavigateOldGame = { gameId -> onOldTarotGame(gameId) }
        )
        @Composable
        fun whistHomeCard() = HomeCard(
            title = stringResource(id = R.string.home_whist),
            modifier = Modifier.weight(1f),
            games = emptyList(),
            onAddNewGame = { },
            onNavigateOldGame = { gameId -> onOldTarotGame(gameId) }
        )
        @Composable
        fun coincheHomeCard() = HomeCard(
            title = stringResource(id = R.string.home_coinche),
            modifier = Modifier.weight(1f),
            games = emptyList(),
            onAddNewGame = { },
            onNavigateOldGame = { gameId -> onOldTarotGame(gameId) }
        )
        @Composable
        fun beloteHomeCard() = HomeCard(
            title = stringResource(id = R.string.home_belote),
            modifier = Modifier.weight(1f),
            games = emptyList(),
            onAddNewGame = { },
            onNavigateOldGame = { gameId -> onOldTarotGame(gameId) }
        )

        when (configuration.orientation) {
            Configuration.ORIENTATION_PORTRAIT -> {
                tarotHomeCard()
                whistHomeCard()
                coincheHomeCard()
                beloteHomeCard()
            }
            else -> {
                Row(modifier = Modifier.weight(1f)) {
                    tarotHomeCard()
                    whistHomeCard()
                }
                Row(modifier = Modifier.weight(1f)) {
                    coincheHomeCard()
                    beloteHomeCard()
                }
            }
        }
    }

}

@ExperimentalFoundationApi
@ExperimentalMaterialApi
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
        ),
        onNewTarotGame = { },
        onOldTarotGame = { }
    )
}