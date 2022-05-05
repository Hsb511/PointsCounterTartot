package com.team23.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TarotViewModel @Inject constructor() : ViewModel() {
    val playersName: MutableState<List<String>> = mutableStateOf(emptyList())
    val totalScores: MutableState<List<Int>> = mutableStateOf(emptyList())
    val scores: MutableState<List<List<Int>>> = mutableStateOf(emptyList())

    init {
        playersName.value = listOf("Laure", "Romane", "Guillaume", "Justine", "Hugo")
        totalScores.value = listOf(1000, 1000, 1000, 1000, 1000)
        scores.value = listOf(listOf(-23, -23, -23, 23, 23), listOf(23, 23, 23, -23, -23))
    }
}