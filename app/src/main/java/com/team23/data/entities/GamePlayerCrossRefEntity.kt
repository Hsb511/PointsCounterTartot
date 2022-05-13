package com.team23.data.entities

import androidx.room.Entity

@Entity(
    tableName = "T_GAME_PLAYER",
    primaryKeys = ["gameId", "playerId"]
)
data class GamePlayerCrossRefEntity(
    val gameId: Int,
    val playerId: Int
)
