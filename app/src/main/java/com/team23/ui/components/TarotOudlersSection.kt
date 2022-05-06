package com.team23.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R

@ExperimentalMaterialApi
@Composable
fun TarotOudlersSection(oudlersAmount: MutableState<Int>) {
    val oudlers = listOf(
        stringResource(id = R.string.tarot_petit),
        stringResource(id = R.string.tarot_monde),
        stringResource(id = R.string.tarot_fool)
    )

    Text(text = "${stringResource(id = R.string.tarot_oudlers)}:")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        items(oudlers) {
            val selected = remember { mutableStateOf(false) }
            FormChip(
                text = it.uppercase(),
                colorState = selected,
                onClick = {
                    selected.value = !selected.value
                    if (selected.value) {
                        oudlersAmount.value ++
                    } else {
                        oudlersAmount.value --
                    }
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotOudlersSectionPreview() {
    TarotOudlersSection(
        oudlersAmount = remember { mutableStateOf(0) }
    )
}
