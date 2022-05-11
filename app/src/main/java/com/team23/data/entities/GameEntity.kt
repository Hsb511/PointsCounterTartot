package com.team23.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class GameEntity(
    @PrimaryKey val id: Int,
    val gameType: Int,
    val startDate: Date
)