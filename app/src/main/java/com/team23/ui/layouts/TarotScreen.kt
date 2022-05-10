package com.team23.ui.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
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
import com.team23.ui.viewmodels.TarotViewModel
import kotlinx.coroutines.launch
import java.util.Collections.addAll

@Composable
fun TarotScreen(tarotViewModel: TarotViewModel = viewModel(), navController: NavHostController) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
    val errorPlayerInvalid = stringResource(id = R.string.error_player_name_invalid)

    TarotScreen(
        players = tarotViewModel.players,
        scores = tarotViewModel.scores,
        isAddingPlayer = tarotViewModel.isAddingPlayer.value,
        isGameStarted = tarotViewModel.isGameStarted.value,
        snackbarHostState = snackbarHostState,
        onAddPlayer = { tarotViewModel.onAddPlayer() },
        onModifierPlayerName = { playerName -> tarotViewModel.onFilterPlayerName(playerName) },
        onAddGame = {
            if (tarotViewModel.onAddNewGame()) {
                navController.navigate("tarotForm")
            } else {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar(errorPlayerInvalid)
                }
            }
        }
    )
}

@Composable
fun TarotScreen(
    players: List<Player>,
    scores: List<List<Int>>,
    isAddingPlayer: Boolean = false,
    isGameStarted: Boolean = true,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onAddPlayer: () -> Unit = {},
    onAddGame: () -> Unit = {},
    onModifierPlayerName: (String) -> String
) {
    val tableWidth = if (isAddingPlayer) 0.86f else 1f

    // TODO FIND A BETTER WAY TO HANDLE THE BUTTON HEIGHT (OR A BETTER UX FOR ADDING PLAYERS ?)
    val rowHeight = 60.dp

    Scaffold(
        modifier = Modifier.padding(8.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddGame() },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { Snackbar(it) }
            )
        }) { padding ->
        Row {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(padding)
                    .fillMaxWidth(tableWidth)
                    .border(
                        1.dp,
                        MaterialTheme.colors.primaryVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                LazyColumn {
                    item {
                        GridHeader(
                            players = players,
                            rowHeight = rowHeight,
                            isGameStarted = isGameStarted,
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
                            shape = RoundedCornerShape(32.dp)
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

@Preview(showSystemUi = true)
@Composable
fun TarotScreenPreview() {
    TarotScreen(
        players = listOf("Laure", "Romane", "Guilla", "Hugo")
            .mapIndexed { id, name -> Player(id, name) },
        scores = listOf(listOf(-23, -23, -23, 23), listOf(23, 23, -23, -23)),
        onModifierPlayerName = { "" }
    )
}