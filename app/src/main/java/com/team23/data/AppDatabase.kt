package com.team23.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.team23.data.AppDatabase.Companion.BDD_VERSION
import com.team23.data.converters.TypeConverter
import com.team23.data.daos.GameDao
import com.team23.data.daos.PlayerDao
import com.team23.data.daos.RoundDao
import com.team23.data.entities.GameEntity
import com.team23.data.entities.GamePlayerCrossRefEntity
import com.team23.data.entities.PlayerEntity
import com.team23.data.entities.RoundEntity

@Database(
    entities = [
        GameEntity::class,
        PlayerEntity::class,
        GamePlayerCrossRefEntity::class,
        RoundEntity::class
    ],
    version = BDD_VERSION
)
@TypeConverters(TypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    companion object {
        const val BDD_VERSION = 1
    }

    abstract fun gameDao(): GameDao
    abstract fun playerDao(): PlayerDao
    abstract fun roundDao(): RoundDao
}