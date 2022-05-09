package com.team23.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.domain.models.Player

@Composable
fun GridHeader(
    players: List<Player>,
    isAddingPlayer: Boolean,
    onPlayerAdded: () -> Unit
) {
    // If isAddingPlayer is true, the button is displayed and thus there is one column more than the
    // players amount
    val columnWeight = 1f / (players.size + isAddingPlayer.compareTo(true) + 1)

    // TODO FIND A BETTER WAY TO HANDLE THE BUTTON HEIGHT (OR A BETTER UX FOR ADDING PLAYERS ?)
    val rowHeight = 60.dp

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(color = MaterialTheme.colors.primary)
    ) {
        items(players) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth(columnWeight)
                    .height(rowHeight)
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
        if (isAddingPlayer) {
            item {
                Button(
                    onClick = { onPlayerAdded() },
                    modifier = Modifier
                        .fillParentMaxWidth(columnWeight)
                        .height(rowHeight)
                        .background(color = MaterialTheme.colors.primary)
                        .border(BorderStroke(1.dp, MaterialTheme.colors.primaryVariant))
                ) {
                    Icon(
                        imageVector = Icons.Filled.AddCircle,
                        contentDescription = "Adding a player"
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GridHeaderPreview() {
    GridHeader(
        players = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
            .mapIndexed { id, name -> Player(id, name) },
        isAddingPlayer = true,
        onPlayerAdded = {}
    )
}