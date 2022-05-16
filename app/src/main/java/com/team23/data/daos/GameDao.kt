package com.team23.data.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.team23.data.entities.GameEmbeddedEntity
import com.team23.data.entities.GameEntity

@Dao
interface GameDao {
    @Query("SELECT * FROM T_GAME WHERE GAMEID = :gameId")
    fun findEmbeddedById(gameId: Int): GameEmbeddedEntity

    @Query("SELECT * FROM T_GAME")
    fun loadAll(): List<GameEmbeddedEntity>

    @Insert
    fun insert(game: GameEntity)
}