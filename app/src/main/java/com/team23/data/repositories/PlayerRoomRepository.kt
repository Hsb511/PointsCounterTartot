package com.team23.data.repositories

import com.team23.data.daos.GamePlayerCrossRefDao
import com.team23.data.daos.PlayerDao
import com.team23.data.entities.GamePlayerCrossRefEntity
import com.team23.domain.extensions.toEntities
import com.team23.domain.models.Player
import com.team23.domain.repositories.PlayerRepository
import javax.inject.Inject

class PlayerRoomRepository @Inject constructor(
    private val playerDao: PlayerDao,
    private val gamePlayerCrossRefDao: GamePlayerCrossRefDao
) : PlayerRepository {
    override suspend fun savePlayers(players: List<Player>, gameId: Int): List<Long> {
        val playersId = playerDao.insertAll(players.toEntities())
        gamePlayerCrossRefDao.insertAll(playersId.map {
            GamePlayerCrossRefEntity(
                gameId = gameId,
                playerId = it.toInt()
            )
        })
        return playersId
    }
}