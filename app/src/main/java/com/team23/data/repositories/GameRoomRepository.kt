package com.team23.data.repositories

import com.team23.data.daos.GameDao
import com.team23.data.daos.GamePlayerCrossRefDao
import com.team23.data.daos.PlayerDao
import com.team23.data.entities.GamePlayerCrossRefEntity
import com.team23.data.extensions.toModel
import com.team23.data.extensions.toModels
import com.team23.domain.extensions.toEntities
import com.team23.domain.extensions.toEntity
import com.team23.domain.models.Game
import com.team23.domain.models.Player
import com.team23.domain.repositories.GameRepository
import javax.inject.Inject

class GameRoomRepository @Inject constructor(
    private val gameDao: GameDao,
    private val gamePlayerDao: GamePlayerCrossRefDao,
    private val playerDao: PlayerDao
) : GameRepository {
    override suspend fun findGameById(gameId: Int) =
        gameDao.findEmbeddedById(gameId)?.toModel()

    override suspend fun loadAllGames(): List<Game> = gameDao.loadAll().toModels()

    override suspend fun saveNewGame(game: Game): Game {
        val gameId = gameDao.insert(game.toEntity())
        val playersId = playerDao.insertAll(game.players.toEntities())
        gamePlayerDao.insertAll(playersId.map {
            GamePlayerCrossRefEntity(
                gameId = gameId.toInt(),
                playerId = it.toInt()
            )
        })
        val newSavedGame = Game(
            id = gameId.toInt(),
            gameType = game.gameType,
            players = game.players.mapIndexed { index, player ->
                Player (
                    id = playersId[index].toInt(),
                    name = player.name
                )
            }
        )
        return newSavedGame
    }
}