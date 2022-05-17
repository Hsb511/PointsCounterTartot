package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.team23.data.entities.GamePlayerCrossRefEntity


@Dao
interface GamePlayerCrossRefDao {
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(gamePlayer: List<GamePlayerCrossRefEntity>)
}