package com.team23.ui.layouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.R
import com.team23.ui.components.TarotChipsSection
import com.team23.ui.components.TarotScoresSection
import com.team23.ui.viewmodels.TarotViewModel

@ExperimentalMaterialApi
@Composable
fun TarotForm(tarotViewModel: TarotViewModel = viewModel(), navController: NavHostController) {
    TarotForm(
        playersName = tarotViewModel.playersName.value,
        onSaveNewGame = {
            tarotViewModel.onSaveNewGame()
            navController.navigate("tarot")
        }
    )
}

@ExperimentalMaterialApi
@Composable
fun TarotForm(
    playersName: List<String>,
    onSaveNewGame: () -> Unit
) {
    Scaffold(
        modifier = Modifier.padding(8.dp),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onSaveNewGame() },
                elevation = FloatingActionButtonDefaults.elevation(8.dp)
            ) {
                Icon(Icons.Filled.Done, "Done")
            }
        }) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TarotChipsSection(
                title = "${stringResource(id = R.string.tarot_taker)}:",
                chipsNameList = playersName
            )

            TarotChipsSection(
                title = "${stringResource(id = R.string.tarot_bid)}:",
                chipsNameList = listOf(
                    stringResource(id = R.string.tarot_small),
                    stringResource(id = R.string.tarot_guard),
                    stringResource(id = R.string.tarot_guard_without),
                    stringResource(id = R.string.tarot_guard_against)
                )
            )

            TarotChipsSection(
                title = "${stringResource(id = R.string.tarot_oudlers)}:",
                chipsNameList = listOf(
                    stringResource(id = R.string.tarot_petit),
                    stringResource(id = R.string.tarot_monde),
                    stringResource(id = R.string.tarot_fool)
                )
            )

            TarotScoresSection()

            if (playersName.size == 5) {
                TarotChipsSection(
                    title = "${stringResource(id = R.string.tarot_takers_partner)}:",
                    chipsNameList = playersName
                )
            }

            TarotChipsSection(
                title = "${stringResource(id = R.string.tarot_bonuses)}:",
                chipsNameList = listOf(
                    stringResource(id = R.string.tarot_handful),
                    stringResource(id = R.string.tarot_misery),
                    stringResource(id = R.string.tarot_slam)
                )
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotFormPreview() {
    TarotForm(
        listOf("Laure", "Romane", "Guilla", "Justin", "Hugo"),
        onSaveNewGame = {}
    )
}
