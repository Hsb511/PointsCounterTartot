package com.team23.data.extensions

import com.team23.data.entities.GameEmbeddedEntity
import com.team23.domain.enums.GameTypeEnum
import com.team23.domain.models.Game

fun GameEmbeddedEntity.toModel() = Game(
    id = this.game.gameId,
    gameType = GameTypeEnum.values()[this.game.gameType],
    players = this.players.toModels(),
    startDate = this.game.startDate
)

fun List<GameEmbeddedEntity>.toModels() = this.map { it.toModel() }