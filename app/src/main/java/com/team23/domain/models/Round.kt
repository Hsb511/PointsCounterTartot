package com.team23.domain.models

import androidx.compose.runtime.MutableState
import com.team23.domain.enums.BidEnum
import com.team23.domain.enums.BonusEnum
import com.team23.domain.enums.OudlerEnum

data class Round(
    val taker: Player,
    val partner: Player?,
    val bid: BidEnum,
    val oudlers: List<OudlerEnum>,
    val attackPoints: Int,
    val bonuses: MutableMap<BonusEnum, MutableState<Boolean>>
)
