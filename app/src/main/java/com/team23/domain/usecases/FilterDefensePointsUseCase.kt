package com.team23.domain.usecases

import javax.inject.Inject

class FilterDefensePointsUseCase @Inject constructor() {
    operator fun invoke(attackPoints: String, defensePoints: String): String {
        return attackPoints.toByteOrNull()?.let {
            if (it > 91) {
                "0"
            } else {
                (91 - it).toString()
            }
        }?: ""
    }
}