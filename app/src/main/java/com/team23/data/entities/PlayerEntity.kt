package com.team23.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "T_PLAYER")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = true)
    val playerId: Int = 0,
    val name: String,
)
