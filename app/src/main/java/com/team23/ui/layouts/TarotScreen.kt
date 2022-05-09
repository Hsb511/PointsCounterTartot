package com.team23.ui.layouts

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.domain.models.Player
import com.team23.ui.components.GridContent
import com.team23.ui.components.GridHeader
import com.team23.ui.viewmodels.TarotViewModel

@Composable
fun TarotScreen(tarotViewModel: TarotViewModel = viewModel(), navController: NavHostController) {
    TarotScreen(
        players = tarotViewModel.players,
        scores = tarotViewModel.scores,
        isAddingPlayer = tarotViewModel.isAddingPlayer.value,
        onAddPlayer = { tarotViewModel.onAddPlayer() },
        onAddGame = { navController.navigate("tarotForm") }
    )
}

@Composable
fun TarotScreen(
    players: List<Player>,
    scores: List<List<Int>>,
    isAddingPlayer: Boolean,
    onAddPlayer: () -> Unit,
    onAddGame: () -> Unit
) {
    // If isAddingPlayer is true, the button is displayed and thus there is one column more than the
    // players amount
    val columnWeight = 1f / (players.size + isAddingPlayer.compareTo(true) + 1)

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
        }) { padding ->
        Row {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .padding(padding)
                    .border(
                        1.dp,
                        MaterialTheme.colors.primaryVariant,
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(columnWeight * players.size)
                ) {
                    item {
                        GridHeader(
                            players = players,
                            rowHeight = rowHeight
                        )
                    }
                    items(scores) {
                        GridContent(scores = it)
                    }
                }
            }

            if (isAddingPlayer) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(rowHeight)
                ) {
                    OutlinedButton(
                        onClick = { onAddPlayer() },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor =
                            MaterialTheme.colors.onSurface
                                .copy(alpha = TextFieldDefaults.BackgroundOpacity)
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Adding a player",
                            tint = MaterialTheme.colors.onSurface,
                        )
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Adding a player",
                            tint = MaterialTheme.colors.onSurface,
                        )
                    }
                    Canvas(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                color = MaterialTheme.colors.onSurface
                                    .copy(alpha = TextFieldDefaults.BackgroundOpacity),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        drawRoundRect(
                            color = Color.Gray,
                            style = Stroke(
                                width = 4f,
                                pathEffect = dashPathEffect(floatArrayOf(10f, 10f), 0f)
                            ),
                            cornerRadius = CornerRadius(8.dp.value * 3, 8.dp.value * 3)
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
        isAddingPlayer = true,
        onAddPlayer = {},
        onAddGame = {}
    )
}