package com.team23.domain.usecases

import com.team23.domain.models.Player
import javax.inject.Inject

class CheckIsPlayerAddingUseCase @Inject constructor() {
    operator fun invoke( players: List<Player>, scores: List<List<Int>>) =
        players.size < 5 && scores.isEmpty()
}
