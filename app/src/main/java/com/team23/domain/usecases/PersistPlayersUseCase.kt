package com.team23.domain.usecases

import com.team23.domain.models.Player
import com.team23.domain.repositories.PlayerRepository
import javax.inject.Inject

class PersistPlayersUseCase @Inject constructor(
    private val playerRepository: PlayerRepository
) {
    suspend fun execute(players: List<Player>, gameId: Int): List<Long> {
        return playerRepository.savePlayers(players, gameId)
    }
}