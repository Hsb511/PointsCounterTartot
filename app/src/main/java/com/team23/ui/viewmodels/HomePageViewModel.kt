package com.team23.ui.viewmodels

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.team23.domain.enums.GameTypeEnum
import com.team23.domain.models.Game
import com.team23.domain.usecases.LoadAllGamesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val loadAllGamesUseCase: LoadAllGamesUseCase
) : ViewModel() {
    val tarotGames = mutableStateListOf<Game>()
    val areGamesLoaded = mutableStateOf(false)

    fun refreshTarotGames() {
        areGamesLoaded.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val allGames = loadAllGamesUseCase.execute()
            withContext(Dispatchers.Main) {
                tarotGames.clear()
                tarotGames.addAll(allGames.filter { it.gameType == GameTypeEnum.FRENCH_TAROT })
            }
        }
        areGamesLoaded.value = true
    }

    init {
        refreshTarotGames()
    }
}