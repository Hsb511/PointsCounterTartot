package com.team23.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R

@Composable
fun TarotScoresSection(
    attackPoints: MutableState<String>,
    defensePoints: MutableState<String>,
    onAttackPointsChanged: (String, String) -> String
) {
    Text(text = "${stringResource(id = R.string.tarot_scores)}:")

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${stringResource(id = R.string.tarot_attack)}:",
                modifier = Modifier.padding(4.dp)
            )
            TextField(
                value = attackPoints.value,
                onValueChange = {
                    attackPoints.value = onAttackPointsChanged(it, defensePoints.value)
                },
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth().padding(4.dp)
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "${stringResource(id = R.string.tarot_defense)}:",
                modifier = Modifier.padding(4.dp)
            )
            TextField(
                value = defensePoints.value,
                onValueChange = {/* TODO */ },
                singleLine = true,
                shape = MaterialTheme.shapes.large,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TarotScoresSectionPreview() {
    TarotScoresSection(
        attackPoints = remember { mutableStateOf("23") },
        defensePoints = remember { mutableStateOf("68") },
        onAttackPointsChanged = String::plus
    )
}