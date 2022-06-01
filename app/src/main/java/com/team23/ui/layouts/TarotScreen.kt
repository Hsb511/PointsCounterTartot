package com.team23.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.R
import com.team23.domain.models.Player
import com.team23.ui.components.GridContent
import com.team23.ui.components.GridHeader
import com.team23.ui.components.PointsCounterBottomBar
import com.team23.ui.viewmodels.TarotViewModel
import kotlinx.coroutines.launch

@ExperimentalComposeUiApi
@Composable
fun TarotScreen(
    tarotViewModel: TarotViewModel = viewModel(),
    navController: NavHostController,
    gameId: String
) {
    tarotViewModel.initGame(gameId)
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val errorPlayerInvalid = stringResource(id = R.string.error_player_name_invalid)

    TarotScreen(
        players = tarotViewModel.players,
        scores = tarotViewModel.scores,
        isAddingPlayer = tarotViewModel.isAddingPlayer.value,
        isGameLoaded = tarotViewModel.isGameLoaded.value,
        isGameStarted = tarotViewModel.isGameStarted.value,
        snackbarHostState = snackbarHostState,
        onAddPlayer = { tarotViewModel.onAddPlayer() },
        onCheckPlayer = { tarotViewModel.checkPlayersNameValid() },
        onModifierPlayerName = { playerName -> tarotViewModel.onFilterPlayerName(playerName) },
        onAddRound = {
            if (tarotViewModel.onAddNewRound()) {
                navController.navigate("tarotForm")
            } else {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(errorPlayerInvalid)
                }
            }
        },
        onNavigateHome = {
            navController.navigate("Home")
        },
        onNavigateSettings = {
            // TODO CREATE THE SCREEN
        }
    )
}

@ExperimentalComposeUiApi
@Composable
fun TarotScreen(
    players: List<Player>,
    scores: List<List<Int>>,
    isAddingPlayer: Boolean = false,
    isGameLoaded: Boolean = true,
    isGameStarted: Boolean = true,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onAddPlayer: () -> Unit = {},
    onCheckPlayer: () -> Boolean = { true },
    onAddRound: () -> Unit = {},
    onModifierPlayerName: (String) -> String,
    onNavigateHome: () -> Unit,
    onNavigateSettings: () -> Unit
) {
    val tableWidth = if (isAddingPlayer) 0.86f else 1f

    // TODO FIND A BETTER WAY TO HANDLE THE BUTTON HEIGHT (OR A BETTER UX FOR ADDING PLAYERS ?)
    val rowHeight = 60.dp

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(),
    ) {
        if (!isGameLoaded) {
            CircularProgressIndicator()
        } else {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = { onAddRound() },
                        elevation = FloatingActionButtonDefaults.elevation(8.dp)
                    ) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                },
                floatingActionButtonPosition = FabPosition.Center,
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        snackbar = { Snackbar(it) }
                    )
                },
                isFloatingActionButtonDocked = true,
                bottomBar = { PointsCounterBottomBar(onNavigateHome, onNavigateSettings) }
            ) { padding ->
                Row (modifier = Modifier.padding(8.dp)) {
                    Card(
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxWidth(tableWidth)
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colors.onSecondary,
                                shape = MaterialTheme.shapes.small
                            )
                    ) {
                        LazyColumn {
                            item {
                                GridHeader(
                                    players = players,
                                    rowHeight = rowHeight,
                                    isGameStarted = isGameStarted,
                                    onCheckPlayer = onCheckPlayer,
                                    onModifierPlayerName = onModifierPlayerName
                                )
                            }
                            items(scores) {
                                GridContent(scores = it)
                            }
                        }
                    }

                    if (isAddingPlayer) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .height(rowHeight)
                                .wrapContentWidth()
                                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                        ) {
                            IconButton(
                                onClick = { onAddPlayer() },
                                modifier = Modifier.background(
                                    color = MaterialTheme.colors.secondary,
                                    shape = MaterialTheme.shapes.large
                                )
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_baseline_person_add_24),
                                    contentDescription = "Adding a player",
                                    tint = MaterialTheme.colors.onSecondary,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@ExperimentalComposeUiApi
@Preview(showSystemUi = true)
@Composable
fun TarotScreenPreview() {
    TarotScreen(
        players = listOf("Laure", "Romane", "Guilla", "Hugo")
            .mapIndexed { id, name -> Player(id, name) },
        scores = listOf(listOf(-23, -23, -23, 23), listOf(23, 23, -23, -23)),
        onModifierPlayerName = { "" },
        onNavigateHome = {},
        onNavigateSettings = {}
    )
}