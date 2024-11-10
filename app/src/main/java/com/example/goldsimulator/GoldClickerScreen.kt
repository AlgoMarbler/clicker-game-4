package com.example.goldsimulator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GoldClickerGame(viewModel: GoldClickerViewModel) {
    val state = viewModel.viewState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = state.goldDisplay,
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = state.goldPerClickDisplay,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = state.prestigeBonusDisplay,
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Button(
            onClick = viewModel::onGoldClick,
            modifier = Modifier.padding(8.dp)
        ) {
            Text(state.goldButtonText)
        }

        Button(
            onClick = viewModel::upgradeGoldPerClick,
            modifier = Modifier.padding(8.dp),
            enabled = state.gold >= state.goldInformation.upgradeCost
        ) {
            Text(state.upgradeButtonText)
        }

        Button(
            onClick = viewModel::activateAutoClicker,
            modifier = Modifier.padding(8.dp),
            enabled = !state.autoClickerEnabled && state.gold >= state.goldInformation.autoClickerCost
        ) {
            Text(state.autoClickerText)
        }

        Button(
            onClick = viewModel::prestige,
            modifier = Modifier.padding(8.dp),
            enabled = state.gold >= 10000
        ) {
            Text(state.prestigeButtonText)
        }

        Text(
            text = state.achievementsHeader,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 12.dp)
        )

        Column {
            for (i in state.achievements.indices) {
                Text(
                    text = "- ${state.achievements[i]}",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(2.dp)
                )
            }
        }

    }
}