package com.team23.domain.usecases

import com.team23.domain.models.Player
import javax.inject.Inject

class CheckAreAllPlayersNameSetUseCase @Inject constructor() {
    operator fun invoke(players: List<Player>) = players.none { it.name.isEmpty() }
}