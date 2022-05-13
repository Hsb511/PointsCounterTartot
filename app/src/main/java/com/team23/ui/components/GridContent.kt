package com.team23.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun GridContent(scores: List<Int>) {
    var takerPosition = -1
    var takerScoreColor = MaterialTheme.colors.onBackground
    val maxScores = scores.filter { it == scores.maxOrNull()!!}
    val minScores = scores.filter { it == scores.minOrNull()!!}
    if (maxScores.size == 1) {
        takerPosition = scores.indexOf(maxScores[0])
        takerScoreColor = MaterialTheme.colors.primary
    } else if (minScores.size == 1) {
        takerPosition =  scores.indexOf(minScores[0])
        takerScoreColor = MaterialTheme.colors.secondary
    }
    LazyRow(modifier = Modifier.fillMaxWidth()) {


        itemsIndexed(scores) { index, score ->
            val isTakersScore = index == takerPosition
            val scoreColor = if (isTakersScore) takerScoreColor
                             else MaterialTheme.colors.onBackground
            val scoreStyle =  if (isTakersScore) MaterialTheme.typography.h6
                              else MaterialTheme.typography.subtitle1

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillParentMaxWidth(1f / scores.size)
                    .border(BorderStroke(1.dp, MaterialTheme.colors.primaryVariant))
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "1",
                        style = MaterialTheme.typography.h6,
                        color = Color.Transparent,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = score.toString(),
                        style = scoreStyle,
                        color = scoreColor,
                        modifier = Modifier.padding(4.dp)
                    )
                    Text(
                        text = "1",
                        style = MaterialTheme.typography.h6,
                        color = Color.Transparent,
                        modifier = Modifier.padding(4.dp)

                    )
                }

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