package com.team23.domain.usecases

import com.team23.domain.enums.BidEnum
import com.team23.domain.models.Player
import javax.inject.Inject

class CheckFormValidityUseCase @Inject constructor() {
    operator fun invoke(players: List<Player>,
                        bid: BidEnum?,
                        attackPoints: String): Boolean {
        return when {
            players.none { it.isTaker } -> false
            bid == null -> false
            attackPoints.toByteOrNull() == null -> false
            else -> true
        }
    }
}