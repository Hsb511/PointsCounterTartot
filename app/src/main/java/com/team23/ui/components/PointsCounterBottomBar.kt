package com.team23.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R

@Composable
fun PointsCounterBottomBar(
    onNavigateHome: () -> Unit,
    onNavigateSettings: () -> Unit
) {
    val selectedIndex = remember { mutableStateOf(0) }
    BottomAppBar(
        cutoutShape = CircleShape,
        backgroundColor = MaterialTheme.colors.secondaryVariant,
        contentPadding = PaddingValues(0.dp)
    ) {
        BottomNavigation(
            backgroundColor = MaterialTheme.colors.secondaryVariant
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Home, "Home",
                        tint = MaterialTheme.colors.onBackground
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.navigation_home),
                        color = MaterialTheme.colors.onBackground
                    )
                },
                selected = (selectedIndex.value == 0),
                onClick = {
                    selectedIndex.value = 0
                    onNavigateHome()
                }
            )

            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = Icons.Default.Settings,
                        "Settings",
                        tint = MaterialTheme.colors.onBackground
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.navigation_settings),
                        color = MaterialTheme.colors.onBackground
                    )
                },
                selected = (selectedIndex.value == 1),
                onClick = {
                    selectedIndex.value = 1
                    onNavigateSettings()
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PointsCounterBottomBarPreview() {
    PointsCounterBottomBar(
        onNavigateHome = {},
        onNavigateSettings = {}
    )
}