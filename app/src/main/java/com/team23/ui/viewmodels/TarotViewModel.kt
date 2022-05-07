package com.team23.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.team23.domain.enums.BidEnum
import com.team23.domain.models.Player
import com.team23.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TarotViewModel @Inject constructor(
    private val filterAttackPointsUseCase: FilterAttackPointsUseCase,
    private val filterDefensePointsUseCase: FilterDefensePointsUseCase,
    private val computeGameScoresUseCase: ComputeGameScoresUseCase,
    private val updatePlayersScoreUseCase: UpdatePlayersScoreUseCase,
    private val checkFormValidityUseCase: CheckFormValidityUseCase,
) : ViewModel() {
    private val defaultBid: BidEnum? = null
    private val defaultOudlersAmount = 0
    private val defaultAttackPoints = ""
    private val defaultDefensePoints = ""

    val players = mutableStateListOf<Player>()
    val bid: MutableState<BidEnum?> = mutableStateOf(defaultBid)
    val oudlersAmount = mutableStateOf(defaultOudlersAmount)
    val attackPoints = mutableStateOf(defaultAttackPoints)
    val defensePoints = mutableStateOf(defaultDefensePoints)
    val scores = mutableStateListOf<List<Int>>()

    init {
        players.addAll(listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
            .mapIndexed { index, value -> Player(index, value) })
    }

    fun onSaveNewGame(): Boolean {
        val isFormValid = checkFormValidityUseCase(players, bid.value, attackPoints.value)
        if (isFormValid) {
            scores.add(
                computeGameScoresUseCase(
                    players,
                    bid.value!!,
                    oudlersAmount.value,
                    attackPoints.value.toInt()
                )
            )
            val totalScores = updatePlayersScoreUseCase(players, scores)
            players.forEachIndexed { index, player ->
                player.score = totalScores[index]
            }
            resetDataForNextGame()
        }
        return isFormValid
    }

    private fun resetDataForNextGame() {
        players.forEach {
            it.isTaker = false
            it.isPartner = false
        }
        bid.value = defaultBid
        oudlersAmount.value = defaultOudlersAmount
        attackPoints.value = defaultAttackPoints
        defensePoints.value = defaultDefensePoints
    }

    fun onFilterAttackPoints(attackPoints: String, defensePoints: String): String {
        this.defensePoints.value = filterDefensePointsUseCase(attackPoints, defensePoints)
        return filterAttackPointsUseCase(attackPoints, defensePoints)
    }
}