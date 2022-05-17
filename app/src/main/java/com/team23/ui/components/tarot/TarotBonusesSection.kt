package com.team23.ui.components.tarot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R
import com.team23.ui.components.FormChip

@ExperimentalMaterialApi
@Composable
fun TarotBonusesSection() {
    val simpleHandfulAttack = remember { mutableStateOf(false) }
    val doubleHandfulAttack = remember { mutableStateOf(false) }
    val tripleHandfulAttack = remember { mutableStateOf(false) }
    val simpleHandfulDefense = remember { mutableStateOf(false) }
    val doubleHandfulDefense = remember { mutableStateOf(false) }
    val tripleHandfulDefense = remember { mutableStateOf(false) }

    Text(text = "${stringResource(id = R.string.tarot_bonuses)}:")

    Column(modifier = Modifier.padding(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${stringResource(id = R.string.tarot_simple_handful)}:",
                modifier = Modifier.width(135.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_attack).uppercase(),
                colorState = simpleHandfulAttack,
                onClick = { simpleHandfulAttack.value = !simpleHandfulAttack.value },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_defense).uppercase(),
                colorState = simpleHandfulDefense,
                onClick = { simpleHandfulDefense.value = !simpleHandfulDefense.value },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${stringResource(id = R.string.tarot_double_handful)}:",
                modifier = Modifier.width(135.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_attack).uppercase(),
                colorState = doubleHandfulAttack,
                onClick = { doubleHandfulAttack.value = !doubleHandfulAttack.value },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_defense).uppercase(),
                colorState = doubleHandfulDefense,
                onClick = { doubleHandfulDefense.value = !doubleHandfulDefense.value },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${stringResource(id = R.string.tarot_triple_handful)}:",
                modifier = Modifier.width(135.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_attack).uppercase(),
                colorState = tripleHandfulAttack,
                onClick = { tripleHandfulAttack.value = !tripleHandfulAttack.value },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_defense).uppercase(),
                colorState = tripleHandfulDefense,
                onClick = { tripleHandfulDefense.value = !tripleHandfulDefense.value },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotBonusesSectionPreview() {

}