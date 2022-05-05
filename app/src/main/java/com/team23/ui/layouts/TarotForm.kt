package com.team23.ui.layouts

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.team23.ui.viewmodels.TarotViewModel

@Composable
fun TarotForm(tarotViewModel: TarotViewModel = viewModel(), navController: NavHostController) {
    TarotForm()
}

@Composable
fun TarotForm() {

}

@Preview(showSystemUi = true)
@Composable
fun TarotFormPreview() {
    TarotForm()
}
