package com.team23.domain.usecases

import javax.inject.Inject

class FilterPlayerNameUseCase @Inject constructor() {
    operator fun invoke(playerName: String) =
        playerName.replace("\\W".toRegex(), "").let {
            it.substring(0, minOf(it.length, 6))
        }
}