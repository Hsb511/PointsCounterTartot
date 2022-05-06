package com.team23.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R
import com.team23.domain.enums.BidEnum

@ExperimentalMaterialApi
@Composable
fun TarotBidsSection(selectedBid: MutableState<BidEnum?>) {
    val bidsStates =
        BidEnum.values().associateWith { remember { mutableStateOf(selectedBid.value == it) } }

    Text(text = "${stringResource(id = R.string.tarot_bid)}:")

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxWidth()
    ) {

        items(BidEnum.values()) {
            FormChip(
                text = stringResource(it.nameId).uppercase(),
                colorState = bidsStates[it]!!,
                onClick = {
                    bidsStates[selectedBid.value]?.value = false
                    selectedBid.value = it
                    bidsStates[selectedBid.value]?.value = true
                }
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotBidsSectionPreview() {
    TarotBidsSection(
        selectedBid = remember { mutableStateOf(BidEnum.GUARD) }
    )
}