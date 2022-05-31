package com.team23.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.enums.BidEnum
import com.team23.domain.enums.BonusEnum
import com.team23.domain.enums.GameTypeEnum
import com.team23.domain.enums.OudlerEnum
import com.team23.domain.models.Game
import com.team23.domain.models.Player
import com.team23.domain.models.Round
import com.team23.domain.usecases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TarotViewModel @Inject constructor(
    private val loadGameUseCase: LoadGameUseCase,
    private val filterPlayerNameUseCase: FilterPlayerNameUseCase,
    private val filterAttackPointsUseCase: FilterAttackPointsUseCase,
    private val filterDefensePointsUseCase: FilterDefensePointsUseCase,
    private val computeGameScoresUseCase: ComputeGameScoresUseCase,
    private val updatePlayersScoreUseCase: UpdatePlayersScoreUseCase,
    private val checkTarotFormValidityUseCase: CheckTarotFormValidityUseCase,
    private val checkIsPlayerAddingUseCase: CheckIsPlayerAddingUseCase,
    private val checkAreAllPlayersNameSetUseCase: CheckAreAllPlayersNameSetUseCase,
    private val handleBonusesValidityUseCase: HandleBonusesValidityUseCase,
    private val persistPlayersUseCase: PersistPlayersUseCase
) : ViewModel() {
    private val defaultBid: BidEnum? = null
    private val defaultAttackPoints = ""
    private val defaultDefensePoints = ""

    var gameId = ""
    lateinit var game: Game
    val isGameLoaded = mutableStateOf(false)
    val players = mutableStateListOf<Player>()
    val bid: MutableState<BidEnum?> = mutableStateOf(defaultBid)
    private val oudlers = mutableStateListOf<OudlerEnum>()
    val attackPoints = mutableStateOf(defaultAttackPoints)
    val defensePoints = mutableStateOf(defaultDefensePoints)
    val scores = mutableStateListOf<List<Int>>()
    val isAddingPlayer = mutableStateOf(true)
    val isGameStarted = mutableStateOf(false)
    val bonuses: MutableMap<BonusEnum, MutableState<Boolean>> = BonusEnum.values()
        .associateWith { mutableStateOf(false) }.toMutableMap()
    val rounds = mutableStateListOf<Round>()

    init {
        players.addAll(listOf("Laure", "Guilla", "Hugo")
            .mapIndexed { index, value -> Player(index, value) })
    }

    fun initGame(newGameId: String) {
        if (gameId != newGameId) {
            gameId = newGameId
            isGameLoaded.value = false
            viewModelScope.launch(Dispatchers.IO) {
                val loadedGame =
                    loadGameUseCase.execute(newGameId.toIntOrNull()!!, GameTypeEnum.FRENCH_TAROT)
                scores.clear()
                players.clear()
                players.addAll(loadedGame.players)
                game = loadedGame
                gameId = loadedGame.id.toString()
                isAddingPlayer.value = checkIsPlayerAddingUseCase(players, scores)
                isGameLoaded.value = true
            }
        }
        isGameLoaded.value = true
    }

    fun onAddPlayer() {
        players.add(Player(0, ""))
        isAddingPlayer.value = checkIsPlayerAddingUseCase(players, scores)
    }

    fun onAddNewRound(): Boolean {
        return if (!isGameStarted.value) {
            val arePlayersOk = checkAreAllPlayersNameSetUseCase(players)
            if (arePlayersOk) {
                viewModelScope.launch(Dispatchers.IO) {
                    persistPlayersUseCase.execute(players, game.id).forEachIndexed { index, playerId ->
                        if (players[index].id == 0) {
                            players[index].id = playerId.toInt()
                        }
                    }
                }
            }
            arePlayersOk
        } else {
            true
        }
    }

    fun onOudlerClicked(oudlerEnum: OudlerEnum) {
        if (oudlers.contains(oudlerEnum)) {
            oudlers.remove(oudlerEnum)
        } else {
            oudlers.add(oudlerEnum)
        }
    }

    fun onBonusClicked(bonusEnum: BonusEnum) {
        bonuses[bonusEnum]!!.value = !bonuses[bonusEnum]!!.value
        handleBonusesValidityUseCase.execute(bonusEnum, bonuses)
    }

    fun onSaveNewRound(): Boolean {
        val isFormValid = checkTarotFormValidityUseCase(players, bid.value, attackPoints.value)
        if (isFormValid) {
            val newRound = Round(
                taker = players.first { it.isTaker },
                partner = players.firstOrNull { it.isPartner },
                bid = bid.value!!,
                oudlers = oudlers,
                attackPoints = attackPoints.value.toInt(),
                bonuses = bonuses
            )
            rounds.add(newRound)
            scores.add(computeGameScoresUseCase(game, newRound))
            val totalScores = updatePlayersScoreUseCase(players, scores)
            isAddingPlayer.value = checkIsPlayerAddingUseCase(players, scores)
            isGameStarted.value = true
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
        oudlers.clear()
        attackPoints.value = defaultAttackPoints
        defensePoints.value = defaultDefensePoints
        bonuses.forEach {
            it.value.value = false
        }
    }

    fun onFilterPlayerName(playerName: String) = filterPlayerNameUseCase(playerName)

    fun onFilterAttackPoints(attackPoints: String, defensePoints: String): String {
        this.defensePoints.value = filterDefensePointsUseCase(attackPoints, defensePoints)
        return filterAttackPointsUseCase(attackPoints, defensePoints)
    }
}