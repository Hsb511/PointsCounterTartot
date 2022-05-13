package com.team23.data.extensions

import com.team23.data.entities.PlayerEntity
import com.team23.domain.models.Player

fun PlayerEntity.toModel() = Player(
    id = this.playerId,
    name = this.name,
)

fun List<PlayerEntity>.toModels(): List<Player> = this.map { it.toModel() }
