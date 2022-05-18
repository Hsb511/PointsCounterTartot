package com.team23.ui.components.tarot

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.team23.R
import com.team23.domain.enums.BonusEnum
import com.team23.domain.models.Player
import com.team23.ui.components.FormChip

@ExperimentalMaterialApi
@Composable
fun TarotBonusesSection(
    players: List<Player>,
    bonuses: MutableMap<BonusEnum, MutableState<Boolean>>,
    onBonusClicked: (BonusEnum) -> Unit
) {
    Text(text = "${stringResource(id = R.string.tarot_bonuses)}:")

    Column(modifier = Modifier.padding(12.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${stringResource(id = R.string.tarot_simple_handful)}:",
                modifier = Modifier.width(135.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_attack).uppercase(),
                colorState = bonuses[BonusEnum.SIMPLE_HANDFUL_ATTACK]!!,
                onClick = { onBonusClicked(BonusEnum.SIMPLE_HANDFUL_ATTACK) },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_defense).uppercase(),
                colorState = bonuses[BonusEnum.SIMPLE_HANDFUL_DEFENSE]!!,
                onClick = { onBonusClicked(BonusEnum.SIMPLE_HANDFUL_DEFENSE) },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${stringResource(id = R.string.tarot_double_handful)}:",
                modifier = Modifier.width(135.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_attack).uppercase(),
                colorState = bonuses[BonusEnum.DOUBLE_HANDFUL_ATTACK]!!,
                onClick = { onBonusClicked(BonusEnum.DOUBLE_HANDFUL_ATTACK) },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_defense).uppercase(),
                colorState = bonuses[BonusEnum.DOUBLE_HANDFUL_DEFENSE]!!,
                onClick = { onBonusClicked(BonusEnum.DOUBLE_HANDFUL_DEFENSE) },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${stringResource(id = R.string.tarot_triple_handful)}:",
                modifier = Modifier.width(135.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_attack).uppercase(),
                colorState = bonuses[BonusEnum.TRIPLE_HANDFUL_ATTACK]!!,
                onClick = { onBonusClicked(BonusEnum.TRIPLE_HANDFUL_ATTACK) },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_defense).uppercase(),
                colorState = bonuses[BonusEnum.TRIPLE_HANDFUL_DEFENSE]!!,
                onClick = { onBonusClicked(BonusEnum.TRIPLE_HANDFUL_DEFENSE) },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${stringResource(id = R.string.tarot_one_at_the_end)}:",
                modifier = Modifier.width(135.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_attack).uppercase(),
                colorState = bonuses[BonusEnum.ONE_AT_END_ATTACK]!!,
                onClick = { onBonusClicked(BonusEnum.ONE_AT_END_ATTACK) },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            FormChip(
                text = stringResource(id = R.string.tarot_defense).uppercase(),
                colorState = bonuses[BonusEnum.ONE_AT_END_DEFENSE]!!,
                onClick = { onBonusClicked(BonusEnum.ONE_AT_END_DEFENSE) },
                modifier = Modifier.padding(4.dp, 0.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "${stringResource(id = R.string.tarot_slam)}:")
            Column {
                Row {
                    FormChip(
                        text = stringResource(id = R.string.tarot_announced_slam).uppercase(),
                        colorState = bonuses[BonusEnum.SLAM_ANNOUNCED]!!,
                        onClick = { onBonusClicked(BonusEnum.SLAM_ANNOUNCED) },
                        modifier = Modifier.padding(4.dp, 0.dp)
                    )
                    FormChip(
                        text = stringResource(id = R.string.tarot_failed_slam).uppercase(),
                        colorState = bonuses[BonusEnum.SLAM_FAILED]!!,
                        onClick = { onBonusClicked(BonusEnum.SLAM_FAILED) },
                        modifier = Modifier.padding(4.dp, 0.dp)
                    )
                }
                Row {
                    FormChip(
                        text = stringResource(id = R.string.tarot_non_announced_slam).uppercase(),
                        colorState = bonuses[BonusEnum.SLAM_NON_ANNOUNCED]!!,
                        onClick = { onBonusClicked(BonusEnum.SLAM_NON_ANNOUNCED) },
                        modifier = Modifier.padding(4.dp, 0.dp)
                    )
                    FormChip(
                        text = stringResource(id = R.string.tarot_defense).uppercase(),
                        colorState = bonuses[BonusEnum.SLAM_DEFENSE]!!,
                        onClick = { onBonusClicked(BonusEnum.SLAM_DEFENSE) },
                        modifier = Modifier.padding(4.dp, 0.dp)
                    )
                }
            }

        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            TarotPlayersSection(
                title = stringResource(id = R.string.tarot_misery),
                players = players,
                isTakerSection = false
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showSystemUi = true)
@Composable
fun TarotBonusesSectionPreview() {

}