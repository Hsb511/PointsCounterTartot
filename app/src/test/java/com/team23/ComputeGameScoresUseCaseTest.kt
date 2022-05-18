package com.team23

import androidx.compose.runtime.mutableStateOf
import com.team23.domain.enums.BidEnum
import com.team23.domain.enums.BonusEnum
import com.team23.domain.models.Player
import com.team23.domain.usecases.ComputeGameScoresUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class ComputeGameScoresUseCaseTest {
    private val computeGameScoresUseCase = ComputeGameScoresUseCase()
    private val firstPlayer = Player(id = 0, name = "first", isTaker = true, isPartner = false)
    private val secondPlayer = Player(id = 1, name = "second", isTaker = false, isPartner = false)
    private val thirdPlayer = Player(id = 2, name = "third", isTaker = false, isPartner = false)
    private val fourthPlayer = Player(id = 3, name = "fourth", isTaker = false, isPartner = false)
    private val fifthPlayer = Player(id = 4, name = "fifth", isTaker = false, isPartner = true)
    private val sixthPlayer = Player(id = 5, name = "sixth", isTaker = true, isPartner = true)
    private val emptyBonuses = BonusEnum.values().associateWith { mutableStateOf(false) }.toMutableMap()

    @Test
    fun `no scores if no players`() {
        val players: List<Player> = emptyList()
        val expectedScores: List<Int> = emptyList()
        assertEquals(
            expectedScores,
            computeGameScoresUseCase(players, BidEnum.GUARD, 2, 23, emptyBonuses)
        )
    }

    @Test
    fun `passed guard with 5 players, 2 oudlers and 69 points`() {
        val players = listOf(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer, fifthPlayer)
        assertEquals(
            listOf(212, -106, -106, -106, 106),
            computeGameScoresUseCase(players, BidEnum.GUARD, 2, 69, emptyBonuses)
        )
    }

    @Test
    fun `failed small with 5 players, a self called taker, 1 oudler and 50 points`() {
        val players = listOf(secondPlayer, secondPlayer, thirdPlayer, fourthPlayer, sixthPlayer)
        assertEquals(
            listOf(26, 26, 26, 26, -104),
            computeGameScoresUseCase(players, BidEnum.SMALL, 1, 50, emptyBonuses)
        )
    }

    @Test
    fun `passed guard against with 4 players, a self called taker, 3 oudlers and 52 points`() {
        val players = listOf(secondPlayer, firstPlayer, thirdPlayer, fourthPlayer)
        assertEquals(
            listOf(-246, 738, -246, -246),
            computeGameScoresUseCase(players, BidEnum.GUARD_AGAINST, 3, 52, emptyBonuses)
        )
    }

    @Test
    fun `passed guard without with 3 players, a self called taker, 0 oudler and 78 points`() {
        val players = listOf(secondPlayer, thirdPlayer, firstPlayer)
        assertEquals(
            listOf(-188, -188, 376),
            computeGameScoresUseCase(players, BidEnum.GUARD_WITHOUT, 0, 78, emptyBonuses)
        )
    }

    @Test
    fun `passed guard with 4 players, simple handful and one a the end for the attack`() {
        val players = listOf(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer)
        val currentBonuses = emptyBonuses
        currentBonuses[BonusEnum.SIMPLE_HANDFUL_ATTACK]!!.value = true
        currentBonuses[BonusEnum.ONE_AT_END_ATTACK]!!.value = true
        assertEquals(
            listOf(318, -106, -106, -106),
            computeGameScoresUseCase(players, BidEnum.GUARD, 2, 49, currentBonuses)
        )
    }

    @Test
    fun `passed guard without with 4 players, one a the end for the defense`() {
        val players = listOf(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer)
        val currentBonuses = emptyBonuses
        currentBonuses[BonusEnum.ONE_AT_END_DEFENSE]!!.value = true
        assertEquals(
            listOf(228, -76, -76, -76),
            computeGameScoresUseCase(players, BidEnum.GUARD_WITHOUT, 3, 40, currentBonuses)
        )
    }

    @Test
    fun `failed small with 4 players, simple handful and one a the end for the attack`() {
        val players = listOf(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer)
        val currentBonuses = emptyBonuses
        currentBonuses[BonusEnum.SIMPLE_HANDFUL_ATTACK]!!.value = true
        currentBonuses[BonusEnum.ONE_AT_END_ATTACK]!!.value = true
        assertEquals(
            listOf(-126, 42, 42, 42),
            computeGameScoresUseCase(players, BidEnum.SMALL, 0, 49, currentBonuses)
        )
    }


    @Test
    fun `passed guard with 4 players, simple handful for the attack`() {
        val players = listOf(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer)
        val currentBonuses = emptyBonuses
        currentBonuses[BonusEnum.SIMPLE_HANDFUL_DEFENSE]!!.value = true
        assertEquals(
            listOf(276, -92, -92, -92),
            computeGameScoresUseCase(players, BidEnum.GUARD, 2, 52, currentBonuses)
        )
    }

    @Test
    fun `passed guard with 4 players, simple handful, one a the end and slam announced for the attack`() {
        val players = listOf(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer)
        val currentBonuses = emptyBonuses
        currentBonuses[BonusEnum.SIMPLE_HANDFUL_ATTACK]!!.value = true
        currentBonuses[BonusEnum.ONE_AT_END_ATTACK]!!.value = true
        currentBonuses[BonusEnum.SLAM_ANNOUNCED]!!.value = true
        assertEquals(
            listOf(1746, -582, -582, -582),
            computeGameScoresUseCase(players, BidEnum.GUARD, 2, 87, currentBonuses)
        )
    }
}