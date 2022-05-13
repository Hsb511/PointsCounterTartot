package com.team23.domain.extensions

import com.team23.data.entities.PlayerEntity
import com.team23.domain.models.Player

fun List<Player>.isNotSelfCalled() = this.none { it.isTaker && it.isPartner }

fun List<Player>.toEntities() = this.map { it.toEntity() }

fun Player.toEntity(): PlayerEntity = PlayerEntity(
    playerId = this.id,
    name = this.name
)