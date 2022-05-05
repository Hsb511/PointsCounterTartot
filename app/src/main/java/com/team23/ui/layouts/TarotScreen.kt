package com.team23.ui.layouts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TarotScreen() {
    Text(text = "Tarot",
        style = MaterialTheme.typography.h5,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth())
}

@Preview(showSystemUi = true)
@Composable
fun TarotScreenPreview() {
    TarotScreen()
}