package com.team23.ui.components.tarot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.domain.models.Player
import com.team23.ui.components.FormChip

@ExperimentalMaterialApi
@Composable
fun TarotPlayersSection(
    title: String,
    players: List<Player>,
    // TODO CHANGE THIS TO TAKE THE BONUSES SECTION INTO ACCOUNT
    isTakerSection: Boolean
) {
    val playersStates =
        players.map { it.id }.associateWith { remember { mutableStateOf(false) } }

    Text(text = title)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(players) {
            FormChip(
                text = it.name.uppercase(),
                colorState = playersStates[it.id]!!,
                onClick = {
                    if (isTakerSection) {
                        players.forEach { player -> player.isTaker = false }
                        it.isTaker = true
                    } else {
                        players.forEach { player -> player.isPartner = false }
                        it.isPartner = true
                    }
                    playersStates.values.forEach { state -> state.value = false }
                    playersStates[it.id]!!.value = true
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotPlayersSectionPreview() {
    TarotPlayersSection(
        title = "taker:",
        players = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
            .mapIndexed { index, value -> Player(index, value) },
        isTakerSection = true
    )
}