package com.team23.domain.models

data class Player(
    val id: Int,
    val name: String,
    var isTaker: Boolean = false,
    var isPartner: Boolean = false
)