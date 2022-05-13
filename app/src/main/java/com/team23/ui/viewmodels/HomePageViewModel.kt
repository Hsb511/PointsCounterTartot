package com.team23.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.enums.GameTypeEnum
import com.team23.domain.models.Game
import com.team23.domain.models.Player
import com.team23.domain.usecases.CreateNewGameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val createNewGameUseCase: CreateNewGameUseCase
) : ViewModel() {
    val tarotGames = mutableStateListOf<Game>()

    fun createNewTarotGame() {
        viewModelScope.launch(Dispatchers.IO) {
            createNewGameUseCase.execute(GameTypeEnum.FRENCH_TAROT)
        }
    }

    /*
    init {
        tarotGames.addAll(
            listOf(
                Game(
                    id = 0,
                    gameType = GameTypeEnum.FRENCH_TAROT,
                    players = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
                        .mapIndexed { index, value -> Player(index, value) }
                ),
                Game(
                    id = 1,
                    gameType = GameTypeEnum.FRENCH_TAROT,
                    startDate = Date(232323232323),
                    players = listOf("Laure", "Guilla", "Hugo")
                        .mapIndexed { index, value -> Player(index, value) },
                    scores = listOf(emptyList())
                ),
                Game(
                    id = 1,
                    gameType = GameTypeEnum.FRENCH_TAROT,
                    startDate = Date(2323232323),
                    players = listOf("Laure", "Romane", "Guilla", "Hugo")
                        .mapIndexed { index, value -> Player(index, value) },
                    scores = listOf(emptyList(), emptyList(), emptyList())
                )
            )
        )
    }*/
}