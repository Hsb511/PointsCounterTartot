package com.team23.ui.layouts

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.ui.components.GridContent
import com.team23.ui.components.GridHeader
import com.team23.ui.viewmodels.TarotViewModel

@Composable
fun TarotScreen(tarotViewModel: TarotViewModel = viewModel(), navController: NavHostController) {
    TarotScreen(
        playersName = tarotViewModel.players.map { it.name },
        totalScores = tarotViewModel.totalScores.value,
        scores = tarotViewModel.scores.value,
        onAddGame = { navController.navigate("tarotForm") }
    )
}

@Composable
fun TarotScreen(
    playersName: List<String>,
    totalScores: List<Int>,
    scores: List<List<Int>>,
    onAddGame: () -> Unit
) {
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
        Column {
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
                    modifier = Modifier.fillMaxWidth()
                ) {
                    item {
                        GridHeader(playersName = playersName, totalScores = totalScores)
                    }
                    items(scores) {
                        GridContent(scores = it)
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
        playersName = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo"),
        totalScores = listOf(0, 0, -46, 0, 46),
        scores = listOf(listOf(-23, -23, -23, 23, 23), listOf(23, 23, -23, -23, 23)),
        onAddGame = {}
    )
}