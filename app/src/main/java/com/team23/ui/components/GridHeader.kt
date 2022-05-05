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

@Composable
fun GridHeader(playersName: List<String>, totalScores: List<Int>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(playersName) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth(1f / playersName.size)
                    .background(color = MaterialTheme.colors.primary)
                    .border(BorderStroke(1.dp, MaterialTheme.colors.primaryVariant))
            ) {
                Text(
                    text = it,
                    color = MaterialTheme.colors.onPrimary,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier.padding(0.dp, 4.dp, 0.dp, 0.dp)
                )
                Text(
                    text = totalScores[playersName.indexOf(it)].toString(),
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
        playersName = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo"),
        totalScores = listOf(1000, 1000, 1000, 1000, 1000)
    )
}