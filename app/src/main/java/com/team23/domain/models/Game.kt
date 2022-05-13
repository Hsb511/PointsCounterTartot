package com.team23.domain.models

import com.team23.domain.enums.GameTypeEnum
import java.util.*

data class Game(
    val id: Int = 0,
    val gameType: GameTypeEnum,
    val players: List<Player>,
    val startDate: Date = Date(),
    val scores: List<List<Int>> = emptyList()
)
