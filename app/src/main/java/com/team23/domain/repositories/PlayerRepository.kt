package com.team23.domain.repositories

import com.team23.domain.models.Player

interface PlayerRepository {
    suspend fun savePlayers(players: List<Player>, gameId: Int): List<Long>
}