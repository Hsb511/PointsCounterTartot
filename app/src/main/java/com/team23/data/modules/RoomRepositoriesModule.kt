package com.team23.data.modules

import com.team23.data.repositories.GameRoomRepository
import com.team23.data.repositories.PlayerRoomRepository
import com.team23.domain.repositories.GameRepository
import com.team23.domain.repositories.PlayerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RoomRepositoriesModule {
    @Binds
    abstract fun bindGameRepository(gameRoomRepositories: GameRoomRepository): GameRepository

    @Binds
    abstract fun bindPlayerRepository(playerRoomRepository: PlayerRoomRepository): PlayerRepository
}