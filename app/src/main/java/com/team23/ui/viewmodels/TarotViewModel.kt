package com.team23.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.team23.domain.enums.BidEnum
import com.team23.domain.models.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TarotViewModel @Inject constructor() : ViewModel() {
    val players = mutableStateListOf<Player>()
    val selectedBid: MutableState<BidEnum?> = mutableStateOf(null)
    val totalScores: MutableState<List<Int>> = mutableStateOf(emptyList())
    val scores: MutableState<List<List<Int>>> = mutableStateOf(emptyList())

    init {
        players.addAll(listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
            .mapIndexed { index, value -> Player(index, value) })
        totalScores.value = listOf(0, 0, -46, 0, 46)
        scores.value = listOf(listOf(-23, -23, -23, 23, 23), listOf(23, 23, -23, -23, 23))
    }

    fun onSaveNewGame() {
        val newScores: MutableList<List<Int>> = scores.value.toMutableList()
        newScores.add(listOf(0, 0, 0, 0, 0))
        scores.value = newScores

        // Resetting the players for next game
        players.forEach {
            it.isTaker = false
            it.isPartner = false
        }
    }
}