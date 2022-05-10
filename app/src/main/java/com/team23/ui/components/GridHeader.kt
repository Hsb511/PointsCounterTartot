package com.team23.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.team23.domain.models.Player

@Composable
fun GridHeader(
    players: List<Player>,
    rowHeight: Dp,
    onModifierPlayerName: (String) -> String,
    isGameStarted: Boolean = false
) {
    LazyRow(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(players) { player ->
            val playerName = remember { mutableStateOf(player.name) }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth(1f / players.size)
                    .height(rowHeight)
                    .border(BorderStroke(1.dp, MaterialTheme.colors.primaryVariant))
                    .background(color = MaterialTheme.colors.primary)
            ) {
                if (isGameStarted) {
                    Text(
                        text = player.name,
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.subtitle1,
                        modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
                    )
                    Text(
                        text = player.score.toString(),
                        color = MaterialTheme.colors.onPrimary,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 8.dp)
                    )
                } else {
                    BasicTextField(
                        value = playerName.value,
                        onValueChange = {
                            playerName.value = onModifierPlayerName(it)
                            player.name = onModifierPlayerName(it)
                        },
                        textStyle = TextStyle(
                            color = MaterialTheme.colors.onPrimary,
                            fontWeight = MaterialTheme.typography.subtitle1.fontWeight,
                            fontSize = MaterialTheme.typography.subtitle1.fontSize,
                            letterSpacing = MaterialTheme.typography.subtitle1.letterSpacing,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
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
        players = remember {
            mutableStateListOf<Player>().apply {
                this.addAll(listOf("Laure", "Romane", "Guilla", "Hugo")
                    .mapIndexed { id, name -> Player(id, name) })
            }
        },
        rowHeight = 60.dp,
        onModifierPlayerName = { "" }
    )
}