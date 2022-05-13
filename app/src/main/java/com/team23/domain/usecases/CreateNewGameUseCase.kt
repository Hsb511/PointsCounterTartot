package com.team23.domain.usecases

import com.team23.domain.enums.GameTypeEnum
import com.team23.domain.models.Game
import com.team23.domain.models.Player
import com.team23.domain.repositories.GameRepository
import javax.inject.Inject

class CreateNewGameUseCase @Inject constructor(
    private val gameRepository: GameRepository
) {
    suspend fun execute(gameTypeEnum: GameTypeEnum) {
        gameRepository.saveNewGame(
            Game(
                gameType = gameTypeEnum,
                players = listOf(
                    Player(name = ""),
                    Player(name = ""),
                    Player(name = "")
                )
            )
        )
    }
}