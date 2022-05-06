package com.team23.domain.usecases

import com.team23.domain.enums.BidEnum
import com.team23.domain.models.Player
import com.team23.domain.models.isNotSelfCalled
import javax.inject.Inject

class ComputeGameScoresUseCase @Inject constructor() {
    operator fun invoke(
        players: List<Player>,
        bid: BidEnum,
        oudlersAmount: Int,
        attackPoints: Int
    ): List<Int> {
        val scoreToDo = when (oudlersAmount) {
            0 -> 56
            1 -> 51
            2 -> 41
            else -> 36
        }
        var baseScore = 25

        if (attackPoints < scoreToDo) {
            baseScore = -25
        }

        baseScore += attackPoints - scoreToDo
        baseScore *= bid.multiplier

        return if (players.size == 5 && players.isNotSelfCalled()) {
            players.map {
                when {
                    it.isTaker -> baseScore * 2
                    it.isPartner -> baseScore
                    else ->  -baseScore
                }
            }
        } else {
            players.map {
                if (it.isTaker) {
                    baseScore * (players.size - 1)
                } else {
                    -baseScore
                }
            }
        }
    }
}