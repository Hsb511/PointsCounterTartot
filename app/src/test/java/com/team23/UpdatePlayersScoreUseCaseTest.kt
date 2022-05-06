package com.team23

import com.team23.domain.models.Player
import com.team23.domain.usecases.UpdatePlayersScoreUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class UpdatePlayersScoreUseCaseTest {
    private val updatePlayersScoreUseCase = UpdatePlayersScoreUseCase()
    private val firstPlayer = Player(id = 0, name = "first", isTaker = true, isPartner = false)
    private val secondPlayer = Player(id = 1, name = "second", isTaker = false, isPartner = false)
    private val thirdPlayer = Player(id = 2, name = "third", isTaker = false, isPartner = false)
    private val fourthPlayer = Player(id = 3, name = "fourth", isTaker = false, isPartner = false)
    private val fifthPlayer = Player(id = 4, name = "fifth", isTaker = false, isPartner = true)
    private val sixthPlayer =
        Player(id = 4, name = "fifth", isTaker = false, isPartner = true, score = 23)

    @Test
    fun `no scores if no players`() {
        val players: List<Player> = emptyList()
        val scores: List<List<Int>> = emptyList()
        val expectedScores: List<Int> = emptyList()
        assertEquals(
            expectedScores,
            updatePlayersScoreUseCase(players, scores)
        )
    }

    @Test
    fun `score computing for a five players game`() {
        val players = listOf(firstPlayer, secondPlayer, thirdPlayer, fourthPlayer, fifthPlayer)
        val scores = listOf(
            listOf(23, 23, 23, -23, -23),
            listOf(23, -23, 23, -23, 23)
        )
        val expectedScores = listOf(46, 0, 46, -46, 0)
        assertEquals(
            expectedScores,
            updatePlayersScoreUseCase(players, scores)
        )
    }

    @Test
    fun `score computing for a three players game with scores`() {
        val players = listOf(sixthPlayer, sixthPlayer, sixthPlayer)
        val scores = listOf(
            listOf(2, -1, -1),
            listOf(-10, -10, 20),
            listOf(-100, 200, -100)
        )
        val expectedScores = listOf(-108, 189, -81)
        assertEquals(
            expectedScores,
            updatePlayersScoreUseCase(players, scores)
        )
    }
}
