package com.team23.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PlayerEntity(
    @PrimaryKey val id: Int,
    val name: String,
)
