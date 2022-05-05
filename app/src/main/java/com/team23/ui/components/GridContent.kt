package com.team23.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
fun GridContent(scores: List<Int>) {
    LazyRow(modifier = Modifier.fillMaxWidth()) {
        items(scores) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth(1f / scores.size)
                    .border(BorderStroke(1.dp, MaterialTheme.colors.primaryVariant))
            ) {
                Text(
                    text = it.toString(),
                    style = MaterialTheme.typography.h6
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun GridContentPreview() {
    GridContent(
        scores = listOf(23, 46, -23, -23, -23)
    )
}