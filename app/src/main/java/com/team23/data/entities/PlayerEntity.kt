package com.team23.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_PLAYER")
data class PlayerEntity(
    @PrimaryKey val playerId: Int,
    val name: String,
)
