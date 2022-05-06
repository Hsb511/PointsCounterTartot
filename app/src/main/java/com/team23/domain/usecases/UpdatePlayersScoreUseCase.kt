package com.team23.domain.usecases

import com.team23.domain.models.Player
import javax.inject.Inject

class UpdatePlayersScoreUseCase @Inject constructor() {
    operator fun invoke(
        players: List<Player>,
        scores: List<List<Int>>): List<Int> {

        // TODO PERSIST THE DATA
        return players.mapIndexed { index, _ ->
            scores.sumOf { it[index] }
        }
    }
}