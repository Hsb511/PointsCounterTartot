package com.team23.data.entities

import androidx.room.Entity

@Entity(primaryKeys = ["gameId", "gameId"])
data class GamePlayerCrossRefEntity(
    val gameId: Int,
    val playerId: Int
)
