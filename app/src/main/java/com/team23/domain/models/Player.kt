package com.team23.domain.models

data class Player(
    val id: Int,
    var name: String,
    var score: Int = 0,
    var isTaker: Boolean = false,
    var isPartner: Boolean = false
)
