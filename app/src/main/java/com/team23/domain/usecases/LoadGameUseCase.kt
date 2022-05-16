package com.team23.domain.usecases

import com.team23.domain.repositories.GameRepository
import javax.inject.Inject

class LoadGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun execute(gameId: Int) = gameRepository.findGameById(gameId)
}