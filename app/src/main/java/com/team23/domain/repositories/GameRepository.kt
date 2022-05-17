package com.team23.domain.repositories

import com.team23.domain.models.Game

interface GameRepository {
    suspend fun findGameById(gameId: Int): Game?
    suspend fun loadAllGames(): List<Game>
    suspend fun saveNewGame(game: Game): Game
}