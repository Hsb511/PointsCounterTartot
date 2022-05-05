package com.team23.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.team23.ui.viewmodels.TarotViewModel

@Composable
fun TarotScreen(tarotViewModel: TarotViewModel = viewModel()) {
    TarotScreen(
        playersName = tarotViewModel.playersName.value,
        totalScores = tarotViewModel.totalScores.value,
        scores = tarotViewModel.scores.value
    )
}

@Composable
fun TarotScreen(
    playersName: List<String>,
    totalScores: List<Int>,
    scores: List<List<Int>>
) {
    Column {
        Text(
            text = "Tarot",
            style = MaterialTheme.typography.h5,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        )
        LazyColumn (modifier = Modifier
            .padding(8.dp)
            .fillMaxSize()
        ) {
            item {
                Text(text = playersName.toString())
            }
            item {
                Text(text = totalScores.toString())
            }
            items(scores) {
                Text(text = it.toString())
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TarotScreenPreview() {
    TarotScreen(
        playersName = listOf("Laure", "Romane", "Guillaume", "Justine", "Hugo"),
        totalScores = listOf(1000, 1000, 1000, 1000, 1000),
        scores = listOf(listOf(-23, -23, -23, 23, 23), listOf(23, 23, 23, -23, -23))
    )
}