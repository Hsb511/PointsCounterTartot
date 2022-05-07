package com.team23.domain.extensions

import com.team23.domain.models.Player

fun List<Player>.isNotSelfCalled() = this.none { it.isTaker && it.isPartner }