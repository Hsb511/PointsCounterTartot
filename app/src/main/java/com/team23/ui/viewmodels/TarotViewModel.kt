package com.team23.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.team23.domain.enums.BidEnum
import com.team23.domain.models.Player
import com.team23.domain.usecases.ComputeGameScoresUseCase
import com.team23.domain.usecases.FilterAttackPointsUseCase
import com.team23.domain.usecases.FilterDefensePointsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TarotViewModel @Inject constructor(
    private val filterAttackPointsUseCase: FilterAttackPointsUseCase,
    private val filterDefensePointsUseCase: FilterDefensePointsUseCase,
    private val computeGameScoresUseCase: ComputeGameScoresUseCase
) : ViewModel() {
    val players = mutableStateListOf<Player>()
    val bid: MutableState<BidEnum?> = mutableStateOf(null)
    val oudlersAmount = mutableStateOf(0)
    val attackPoints = mutableStateOf("0")
    val defensePoints = mutableStateOf("0")
    val totalScores: MutableState<List<Int>> = mutableStateOf(emptyList())
    val scores = mutableStateListOf<List<Int>>(emptyList())

    init {
        players.addAll(listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
            .mapIndexed { index, value -> Player(index, value) })
        totalScores.value = listOf(0, 0, -46, 0, 46)
        scores.add(listOf(-23, -23, -23, 23, 23))
        scores.add(listOf(23, 23, -23, -23, 23))
    }

    fun onSaveNewGame() {
        scores.add(computeGameScoresUseCase())

        resetDataForNextGame()
    }

    private fun resetDataForNextGame() {
        players.forEach {
            it.isTaker = false
            it.isPartner = false
        }
        bid.value = null
        oudlersAmount.value = 0
    }

    fun onFilterAttackPoints(attackPoints: String, defensePoints: String): String {
        this.defensePoints.value = filterDefensePointsUseCase(attackPoints, defensePoints)
        return filterAttackPointsUseCase(attackPoints, defensePoints)
    }
}