package com.team23.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.R
import com.team23.domain.enums.BidEnum
import com.team23.domain.models.Player
import com.team23.ui.components.TarotBidsSection
import com.team23.ui.components.TarotPlayersSection
import com.team23.ui.components.TarotScoresSection
import com.team23.ui.viewmodels.TarotViewModel

@ExperimentalMaterialApi
@Composable
fun TarotForm(tarotViewModel: TarotViewModel = viewModel(), navController: NavHostController) {
    TarotForm(
        players = tarotViewModel.players,
        selectedBid = tarotViewModel.selectedBid,
        onSaveNewGame = {
            tarotViewModel.onSaveNewGame()
            navController.navigate("tarot")
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun TarotForm(
    players: List<Player>,
    selectedBid: MutableState<BidEnum?>,
    onSaveNewGame: () -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(8.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onSaveNewGame() },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Filled.Done, "Done")
            }
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TarotPlayersSection(
                title = "${stringResource(id = R.string.tarot_taker)}:",
                players = players,
                isTakerSection = true
            )

            TarotBidsSection(selectedBid)

            /*
            TarotChipsSection(
                title = "${stringResource(id = R.string.tarot_bid)}:",
                chipsNameList = listOf(
                    stringResource(id = R.string.tarot_small),
                    stringResource(id = R.string.tarot_guard),
                    stringResource(id = R.string.tarot_guard_without),
                    stringResource(id = R.string.tarot_guard_against)
                ).associateWith { false }.toMutableMap()
            )

            TarotChipsSection(
                title = "${stringResource(id = R.string.tarot_oudlers)}:",
                chipsNameList = listOf(
                    stringResource(id = R.string.tarot_petit),
                    stringResource(id = R.string.tarot_monde),
                    stringResource(id = R.string.tarot_fool)
                ).associateWith { false }.toMutableMap()
            )*/

            TarotScoresSection()

            if (players.size == 5) {
                TarotPlayersSection(
                    title = "${stringResource(id = R.string.tarot_takers_partner)}:",
                    players = players,
                    isTakerSection = false
                )
            }

            /*
            TarotChipsSection(
                title = "${stringResource(id = R.string.tarot_bonuses)}:",
                chipsNameList = listOf(
                    stringResource(id = R.string.tarot_handful),
                    stringResource(id = R.string.tarot_misery),
                    stringResource(id = R.string.tarot_slam)
                ).associateWith { false }.toMutableMap()
            )*/
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotFormPreview() {
    TarotForm(
        players = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
            .mapIndexed { index, value -> Player(index, value) },
        selectedBid = remember { mutableStateOf(BidEnum.GUARD)},
        onSaveNewGame = {}
    )
}
