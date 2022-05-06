package com.team23.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.domain.models.Player

@Composable
fun GridHeader(players: List<Player>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(players) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth(1f / players.size)
                    .background(color = MaterialTheme.colors.primary)
                    .border(BorderStroke(1.dp, MaterialTheme.colors.primaryVariant))
            ) {
                Text(
                    text = it.name,
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
                )
                Text(
                    text = it.score.toString(),
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GridHeaderPreview() {
    GridHeader(
        players = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
            .mapIndexed { id, name -> Player(id, name) }
    )
}