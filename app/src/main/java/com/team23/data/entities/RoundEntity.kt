package com.team23.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_ROUND")
data class RoundEntity(
    @PrimaryKey val roundId: Int,
    val gameId: Int,
)

