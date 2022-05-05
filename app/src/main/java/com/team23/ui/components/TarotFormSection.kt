package com.team23.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Chip
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@ExperimentalMaterialApi
@Composable
fun TarotFormSection(
    title: String,
    chipsNameList: List<String>
) {
    Text(text = title)

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        items(chipsNameList) {
            Chip(onClick = { /* TODO */ }) {
                Text(text = it.uppercase())
            }
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotFormSectionPreview() {
    TarotFormSection(
        title = "taker:",
        chipsNameList = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
    )
}