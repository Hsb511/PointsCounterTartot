package com.team23.ui.components.tarot

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R
import com.team23.domain.enums.OudlerEnum
import com.team23.ui.components.FormChip

@ExperimentalMaterialApi
@Composable
fun TarotOudlersSection(onOudlerClicked: (OudlerEnum) -> Unit) {
    Text(text = "${stringResource(id = R.string.tarot_oudlers)}:")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        items(OudlerEnum.values()) {
            val selected = remember { mutableStateOf(false) }
            FormChip(
                text = stringResource(it.nameResId).uppercase(),
                colorState = selected,
                onClick = {
                    selected.value = !selected.value
                    onOudlerClicked(it)
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotOudlersSectionPreview() {
    TarotOudlersSection(onOudlerClicked = { })
}
