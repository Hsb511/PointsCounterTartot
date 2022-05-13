package com.team23.domain.repositories

import com.team23.domain.models.Game

interface GameRepository {
    fun loadGames(): List<Game>
    suspend fun saveNewGame(game: Game)
}