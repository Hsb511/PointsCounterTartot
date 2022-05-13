package com.team23.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class GameEmbeddedEntity(
    @Embedded val game: GameEntity,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "playerId",
        associateBy = Junction(GamePlayerCrossRefEntity::class)
    )
    val players: List<PlayerEntity>,
    @Relation(
        parentColumn = "gameId",
        entityColumn = "gameId"
    )
    val rounds: List<RoundEntity>
)
