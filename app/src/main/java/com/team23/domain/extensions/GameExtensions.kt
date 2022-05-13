package com.team23.domain.extensions

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import com.team23.data.entities.GameEmbeddedEntity
import com.team23.data.entities.GameEntity
import com.team23.domain.models.Game

const val SMALL_DATE_FORMAT = "dd/MM/yy"

@SuppressLint("SimpleDateFormat")
fun Game.getSmallDate(): String = SimpleDateFormat(SMALL_DATE_FORMAT).format(this.startDate)

fun Game.toEntity(): GameEntity = GameEntity(
    gameId = this.id,
    gameType = this.gameType.ordinal,
    startDate = startDate
)

fun Game.toEmbeddedEntity(): GameEmbeddedEntity = GameEmbeddedEntity(
    game = this.toEntity(),
    players = this.players.toEntities(),
    rounds = emptyList()
)