package com.team23.domain.usecases

import javax.inject.Inject

class FilterAttackPointsUseCase @Inject constructor() {
    operator fun invoke(attackPoints: String, defensePoints: String): String {
        return attackPoints.toByteOrNull()?.let {
            if (it > 91) {
                "91"
            } else {
                it.toString()
            }
        } ?: ""
    }
}