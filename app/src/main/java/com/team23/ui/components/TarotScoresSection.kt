package com.team23.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R

@Composable
fun TarotScoresSection() {
    Text(text = "${stringResource(id = R.string.tarot_scores)}:")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp, 8.dp, 0.dp, 8.dp)
    ) {
        Text(
            text = "${stringResource(id = R.string.tarot_attack)}:",
            modifier = Modifier.weight(1f)
        )
        TextField(
            value = "23",
            onValueChange = {/* TODO */ },
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.weight(3.8f)
        )
        Text(
            text = "${stringResource(id = R.string.tarot_defense)}:",
            modifier = Modifier.weight(1f).padding(8.dp, 0.dp, 0.dp, 0.dp)
        )
        TextField(
            value = "23",
            onValueChange = {/* TODO */ },
            shape = RoundedCornerShape(32.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            modifier = Modifier.weight(3.8f)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun TarotScoresSectionPreview() {
    TarotScoresSection()
}