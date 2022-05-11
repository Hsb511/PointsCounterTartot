package com.team23.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoundEntity(
    @PrimaryKey val id: Int,
    val gameId: Int,
)

