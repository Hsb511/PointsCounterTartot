package com.team23.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "T_GAME")
data class GameEntity(
    @PrimaryKey val gameId: Int,
    val gameType: Int,
    val startDate: Date
)