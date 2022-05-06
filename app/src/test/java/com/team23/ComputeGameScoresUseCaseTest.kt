package com.team23

import com.team23.domain.enums.BidEnum
import com.team23.domain.models.Player
import com.team23.domain.usecases.ComputeGameScoresUseCase
import org.junit.Test

import org.junit.Assert.*

class ComputeGameScoresUseCaseTest {
    private val computeGameScoresUseCase = ComputeGameScoresUseCase()
    private val firstPlayer = Player(id = 0, name = "first", isTaker = true, isPartner = false)
    private val secondPlayer = Player(id = 1, name = "second", isTaker = false, isPartner = false)
    private val thirdPlayer = Player(id = 2, name = "third", isTaker = false, isPartner = false)
    private val fourthPlayer = Player(id = 3, name = "fourth", isTaker = false, isPartner = false)
    private val fifthPlayer = Player(id = 4, name = "fifth", isTaker = false, isPartner = true)
    private val sixthPlayer = Player(id = 5, name = "sixth", isTaker = true, isPartner = true)

    @Test
    fun `no scores if no players`() {
        val players: List<Player> = emptyList()
        val expectedScores: List<Int> = emptyList()
        assertEquals(
            expectedScores,
            computeGameScoresUseCase(players, BidEnum.GUARD, 2, 23)
        )
    }

    @Test
    fun `passed guard with 5 players, 2 oudlers and 69 points`() {
        val players = listOf(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer, fifthPlayer)
        assertEquals(
            listOf(212, -106, -106, -106, 106),
            computeGameScoresUseCase(players, BidEnum.GUARD, 2, 69)
        )
    }

    @Test
    fun `failed small with 5 players, a self called taker, 1 oudler and 50 points`() {
        val players = listOf(secondPlayer, secondPlayer, thirdPlayer, fourthPlayer, sixthPlayer)
        assertEquals(
            listOf(26, 26, 26, 26, -104),
            computeGameScoresUseCase(players, BidEnum.SMALL, 1, 50)
        )
    }

    @Test
    fun `passed guard against with 4 players, a self called taker, 3 oudlers and 52 points`() {
        val players = listOf(secondPlayer, firstPlayer, thirdPlayer, fourthPlayer)
        assertEquals(
            listOf(-246, 738, -246, -246),
            computeGameScoresUseCase(players, BidEnum.GUARD_AGAINST, 3, 52)
        )
    }

    @Test
    fun `passed guard without with 3 players, a self called taker, 0 oudler and 78 points`() {
        val players = listOf(secondPlayer, thirdPlayer, firstPlayer)
        assertEquals(
            listOf(-188, -188, 376),
            computeGameScoresUseCase(players, BidEnum.GUARD_WITHOUT, 0, 78)
        )
    }
}