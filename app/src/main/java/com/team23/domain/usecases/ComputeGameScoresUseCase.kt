package com.team23.domain.usecases

import javax.inject.Inject

class ComputeGameScoresUseCase @Inject constructor() {
    operator fun invoke(): List<Int> {
        return listOf(0, 0, 0, 0, 0)
    }
}