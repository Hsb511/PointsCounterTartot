package com.team23.domain.usecases

import androidx.compose.runtime.MutableState
import com.team23.domain.enums.BonusEnum
import javax.inject.Inject

class HandleBonusesValidityUseCase @Inject constructor() {
    fun execute(
        bonus: BonusEnum,
        bonuses: MutableMap<BonusEnum, MutableState<Boolean>>
    ) {
        when(bonus) {
            BonusEnum.SIMPLE_HANDFUL_ATTACK -> {
                bonuses[BonusEnum.DOUBLE_HANDFUL_ATTACK]!!.value = false
                bonuses[BonusEnum.TRIPLE_HANDFUL_ATTACK]!!.value = false
            }
            BonusEnum.DOUBLE_HANDFUL_ATTACK -> {
                bonuses[BonusEnum.SIMPLE_HANDFUL_ATTACK]!!.value = false
                bonuses[BonusEnum.TRIPLE_HANDFUL_ATTACK]!!.value = false
            }
            BonusEnum.TRIPLE_HANDFUL_ATTACK -> {
                bonuses[BonusEnum.SIMPLE_HANDFUL_ATTACK]!!.value = false
                bonuses[BonusEnum.DOUBLE_HANDFUL_ATTACK]!!.value = false
            }
            BonusEnum.SIMPLE_HANDFUL_DEFENSE -> {
                bonuses[BonusEnum.DOUBLE_HANDFUL_DEFENSE]!!.value = false
                bonuses[BonusEnum.TRIPLE_HANDFUL_DEFENSE]!!.value = false
            }
            BonusEnum.DOUBLE_HANDFUL_DEFENSE -> {
                bonuses[BonusEnum.SIMPLE_HANDFUL_DEFENSE]!!.value = false
                bonuses[BonusEnum.TRIPLE_HANDFUL_DEFENSE]!!.value = false
            }
            BonusEnum.TRIPLE_HANDFUL_DEFENSE -> {
                bonuses[BonusEnum.SIMPLE_HANDFUL_DEFENSE]!!.value = false
                bonuses[BonusEnum.DOUBLE_HANDFUL_DEFENSE]!!.value = false
            }
            BonusEnum.ONE_AT_END_ATTACK -> bonuses[BonusEnum.ONE_AT_END_DEFENSE]!!.value = false
            BonusEnum.ONE_AT_END_DEFENSE -> bonuses[BonusEnum.ONE_AT_END_ATTACK]!!.value = false
            BonusEnum.SLAM_ANNOUNCED -> {
                bonuses[BonusEnum.SLAM_NON_ANNOUNCED]!!.value = false
                bonuses[BonusEnum.SLAM_DEFENSE]!!.value = false
                bonuses[BonusEnum.SLAM_FAILED]!!.value = false
            }
            BonusEnum.SLAM_NON_ANNOUNCED -> {
                bonuses[BonusEnum.SLAM_ANNOUNCED]!!.value = false
                bonuses[BonusEnum.SLAM_DEFENSE]!!.value = false
                bonuses[BonusEnum.SLAM_FAILED]!!.value = false
            }
            BonusEnum.SLAM_FAILED -> {
                bonuses[BonusEnum.SLAM_ANNOUNCED]!!.value = false
                bonuses[BonusEnum.SLAM_NON_ANNOUNCED]!!.value = false
                bonuses[BonusEnum.SLAM_DEFENSE]!!.value = false
            }
            BonusEnum.SLAM_DEFENSE -> {
                bonuses[BonusEnum.SLAM_ANNOUNCED]!!.value = false
                bonuses[BonusEnum.SLAM_NON_ANNOUNCED]!!.value = false
                bonuses[BonusEnum.SLAM_FAILED]!!.value = false
            }
        }
    }
}