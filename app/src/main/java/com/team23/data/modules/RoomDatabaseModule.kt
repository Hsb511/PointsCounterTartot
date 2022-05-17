package com.team23.data.modules

import android.content.Context
import androidx.room.Room
import com.team23.data.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RoomDatabaseModule {
    private const val LOCAL_DB_NAME = "points_counter_tartot.sqlite"
    private var appDatabase: AppDatabase? = null

    @Provides
    fun provideMonBicDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase =
        appDatabase ?: Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            LOCAL_DB_NAME
        ).build()

    @Provides
    fun provideGameDao(db: AppDatabase) = db.gameDao()

    @Provides
    fun providePlayerDao(db: AppDatabase) = db.playerDao()

    @Provides
    fun provideRoundDao(db: AppDatabase) = db.roundDao()

    @Provides
    fun provideGamePlayerCrossRefDao(db: AppDatabase) = db.gamePlayerCrossRefDao()
}