package com.team23.data.repositories

import com.team23.data.daos.GameDao
import com.team23.data.extensions.toModels
import com.team23.domain.extensions.toEntity
import com.team23.domain.models.Game
import com.team23.domain.repositories.GameRepository
import javax.inject.Inject

class GameRoomRepository @Inject constructor(
    private val gameDao: GameDao
) : GameRepository {
    override fun loadGames(): List<Game> = gameDao.loadAll().toModels()

    override suspend fun saveNewGame(game: Game) {
        gameDao.insert(game.toEntity())
    }
}