package com.team23.domain.usecases

import androidx.compose.runtime.MutableState
import com.team23.domain.enums.BidEnum
import com.team23.domain.enums.BonusEnum
import com.team23.domain.extensions.isNotSelfCalled
import com.team23.domain.models.Player
import javax.inject.Inject

class ComputeGameScoresUseCase @Inject constructor() {
    operator fun invoke(
        players: List<Player>,
        bid: BidEnum,
        oudlersAmount: Int,
        attackPoints: Int,
        bonuses: MutableMap<BonusEnum, MutableState<Boolean>>
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

        var score = baseScore + attackPoints - scoreToDo
        if (bonuses[BonusEnum.ONE_AT_END_ATTACK]!!.value) {
            score += 10
        } else if (bonuses[BonusEnum.ONE_AT_END_DEFENSE]!!.value) {
            score -= 10
        }
        score *= bid.multiplier

        if (baseScore > 0) {
            when {
                bonuses[BonusEnum.SIMPLE_HANDFUL_ATTACK]!!.value -> score += 20
                bonuses[BonusEnum.DOUBLE_HANDFUL_ATTACK]!!.value -> score += 30
                bonuses[BonusEnum.TRIPLE_HANDFUL_ATTACK]!!.value -> score += 40
            }
            when {
                bonuses[BonusEnum.SIMPLE_HANDFUL_DEFENSE]!!.value -> score += 20
                bonuses[BonusEnum.DOUBLE_HANDFUL_DEFENSE]!!.value -> score += 30
                bonuses[BonusEnum.TRIPLE_HANDFUL_DEFENSE]!!.value -> score += 40
            }
        } else {
            when {
                bonuses[BonusEnum.SIMPLE_HANDFUL_ATTACK]!!.value -> score -= 20
                bonuses[BonusEnum.DOUBLE_HANDFUL_ATTACK]!!.value -> score -= 30
                bonuses[BonusEnum.TRIPLE_HANDFUL_ATTACK]!!.value -> score -= 40
            }
            when {
                bonuses[BonusEnum.SIMPLE_HANDFUL_DEFENSE]!!.value -> score -= 20
                bonuses[BonusEnum.DOUBLE_HANDFUL_DEFENSE]!!.value -> score -= 30
                bonuses[BonusEnum.TRIPLE_HANDFUL_DEFENSE]!!.value -> score -= 40
            }
        }

        when {
            bonuses[BonusEnum.SLAM_ANNOUNCED]!!.value -> score += 400
            bonuses[BonusEnum.SLAM_NON_ANNOUNCED]!!.value -> score += 200
            bonuses[BonusEnum.SLAM_FAILED]!!.value -> score -= 200
            bonuses[BonusEnum.SLAM_DEFENSE]!!.value -> score -= 200
        }

        return if (players.size == 5 && players.isNotSelfCalled()) {
            players.map {
                when {
                    it.isTaker -> score * 2
                    it.isPartner -> score
                    else ->  -score
                }
            }
        } else {
            players.map {
                if (it.isTaker) {
                    score * (players.size - 1)
                } else {
                    -score
                }
            }
        }
    }
}