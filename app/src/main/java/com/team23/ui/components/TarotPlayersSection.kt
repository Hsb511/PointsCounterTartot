package com.team23.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.domain.models.Player

@ExperimentalMaterialApi
@Composable
fun TarotPlayersSection(
    title: String,
    players: List<Player>,
    isTakerSection: Boolean
) {
    Text(text = title)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(players) {
            var isSelected by remember {
                mutableStateOf(if (isTakerSection) it.isTaker else it.isPartner)
            }
            Chip(
                onClick = {
                    isSelected = !isSelected
                    if (isTakerSection) {
                        it.isTaker = isSelected
                    } else {
                        it.isPartner = isSelected
                    }
                },
                colors = ChipDefaults.chipColors(
                    backgroundColor = when {
                        isSelected -> MaterialTheme.colors.primary
                        else -> MaterialTheme.colors.primarySurface
                    }
                )
            ) {
                Text(text = it.name.uppercase())
            }
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