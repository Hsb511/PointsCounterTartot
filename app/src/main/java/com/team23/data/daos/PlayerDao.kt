package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import com.team23.data.entities.PlayerEntity

@Dao
interface PlayerDao {

    @Insert
    fun insertAll(players: List<PlayerEntity>): List<Long>
}