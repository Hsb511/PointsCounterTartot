package com.team23.domain.usecases

import com.team23.domain.repositories.GameRepository
import javax.inject.Inject

class LoadAllGamesUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun execute() = gameRepository.loadAllGames()
}