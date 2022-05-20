package com.team23.ui.layouts

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.team23.domain.enums.BonusEnum
import com.team23.domain.enums.OudlerEnum
import com.team23.domain.models.Player
import com.team23.ui.components.PointsCounterBottomBar
import com.team23.ui.components.TarotScoresSection
import com.team23.ui.components.tarot.TarotBidsSection
import com.team23.ui.components.tarot.TarotBonusesSection
import com.team23.ui.components.tarot.TarotOudlersSection
import com.team23.ui.components.tarot.TarotPlayersSection
import com.team23.ui.viewmodels.TarotViewModel

@ExperimentalMaterialApi
@Composable
fun TarotForm(tarotViewModel: TarotViewModel = viewModel(), navController: NavHostController) {
    TarotForm(
        players = tarotViewModel.players,
        selectedBid = tarotViewModel.bid,
        attackPoints = tarotViewModel.attackPoints,
        defensePoints = tarotViewModel.defensePoints,
        onSaveNewGame = {
            if (tarotViewModel.onSaveNewGame()) {
                navController.navigate("tarot/${tarotViewModel.game.id}")
            }
        },
        onOudlerClicked = { oudlerEnum -> tarotViewModel.onOudlerClicked(oudlerEnum) } ,
        onAttackPointsChanged = { attackPoints, defensePoints ->
            tarotViewModel.onFilterAttackPoints(attackPoints, defensePoints)
        },
        bonuses = tarotViewModel.bonuses,
        onBonusClicked =  { bonus -> tarotViewModel.onBonusClicked(bonus) },
        onNavigateHome = {
            navController.navigate("Home")
        },
        onNavigateSettings = {
            // TODO CREATE THE SCREEN
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun TarotForm(
    players: List<Player>,
    selectedBid: MutableState<BidEnum?>,
    attackPoints: MutableState<String>,
    defensePoints: MutableState<String>,
    onSaveNewGame: () -> Unit,
    onOudlerClicked: (OudlerEnum) -> Unit,
    onAttackPointsChanged: (String, String) -> String,
    bonuses: MutableMap<BonusEnum, MutableState<Boolean>>,
    onBonusClicked: (BonusEnum) -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateSettings: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onSaveNewGame() },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Filled.Done, "Done")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = { PointsCounterBottomBar(onNavigateHome, onNavigateSettings) }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(8.dp, 8.dp, 8.dp, 56.dp)) {
            item {
                TarotPlayersSection(
                    title = "${stringResource(id = R.string.tarot_taker)}:",
                    players = players,
                    isTakerSection = true
                )
            }

            item {
                TarotBidsSection(selectedBid)
            }

            item {
                TarotOudlersSection(onOudlerClicked)
            }

            item {
                if (players.size == 5) {
                    TarotPlayersSection(
                        title = "${stringResource(id = R.string.tarot_takers_partner)}:",
                        players = players,
                        isTakerSection = false
                    )
                }
            }

            item {
                TarotScoresSection(attackPoints, defensePoints, onAttackPointsChanged)
            }
            item {
                TarotBonusesSection(players, bonuses, onBonusClicked)
            }
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
        selectedBid = remember { mutableStateOf(BidEnum.GUARD) },
        attackPoints = remember { mutableStateOf("68") },
        defensePoints = remember { mutableStateOf("23") },
        onOudlerClicked = {},
        onSaveNewGame = {},
        onAttackPointsChanged = String::plus,
        bonuses = mutableMapOf(),
        onBonusClicked = {},
        onNavigateHome = {},
        onNavigateSettings = {}
    )
}
