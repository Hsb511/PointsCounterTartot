package com.team23.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R
import com.team23.domain.enums.GameTypeEnum
import com.team23.domain.extensions.getSmallDate
import com.team23.domain.models.Game
import com.team23.domain.models.Player

@Composable
inline fun HomeCard(
    title: String,
    games: List<Game>,
    modifier: Modifier = Modifier,
    crossinline content: @Composable () -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Scaffold(
            modifier = Modifier.padding(4.dp),
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { /* TODO */ },
                    elevation = FloatingActionButtonDefaults.elevation(8.dp)
                ) {
                    Icon(Icons.Filled.Add, "Add")
                }
            }) { padding ->
            Column(modifier = Modifier
                .padding(padding)
                .fillMaxWidth()) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(0.dp, 4.dp, 0.dp, 8.dp)
                )

                LazyColumn {
                    items(games) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .background(
                                    color = MaterialTheme.colors.primary,
                                    shape = RoundedCornerShape(16.dp)
                                )
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "players amount",
                                modifier = Modifier.padding(8.dp, 8.dp, 0.dp, 8.dp)
                            )
                            Text(
                                text = it.players.size.toString(),
                                modifier = Modifier.padding(0.dp, 8.dp, 8.dp, 8.dp)
                            )
                            Icon(
                                imageVector = Icons.Filled.DateRange,
                                contentDescription = "creation date",
                                modifier = Modifier.padding(0.dp, 8.dp)
                            )
                            Text(
                                text = it.getSmallDate(),
                                modifier = Modifier.padding(4.dp, 8.dp, 8.dp, 8.dp)
                            )
                            Icon(
                                imageVector = Icons.Filled.Refresh,
                                contentDescription = "game rounds",
                                modifier = Modifier.padding(0.dp, 8.dp)
                            )
                            Text(
                                text = "${it.scores.size} ${stringResource(R.string.home_round)}",
                                modifier = Modifier.padding(4.dp, 8.dp)
                            )
                        }
                    }
                }

                content()
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeCardPreview() {
    HomeCard(title = "french tarot", games = listOf(
        Game(
            id = 0,
            gameType = GameTypeEnum.FRENCH_TAROT,
            players = listOf("Laure", "Romane", "Guilla", "Justin", "Hugo")
                .mapIndexed { index, value -> Player(index, value) }
        )
    ), //onAddNewGame = {}
    ) { }
}