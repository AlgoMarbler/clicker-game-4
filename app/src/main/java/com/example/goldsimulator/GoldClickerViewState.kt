package com.example.goldsimulator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GoldClickerGame(viewModel: GoldClickerViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Gold: ${viewModel.gold}",
            fontSize = 32.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Gold per Click: ${viewModel.goldPerClick}",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Prestige Bonus: x${viewModel.prestigeBonus}",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Button(
            onClick = { viewModel.onGoldClick() },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Click to Earn Gold")
        }

        Button(
            onClick = { viewModel.upgradeGoldPerClick() },
            modifier = Modifier.padding(16.dp),
            enabled = viewModel.gold >= viewModel.upgradeCost
        ) {
            Text("Upgrade (+1 GPC) - Cost: ${viewModel.upgradeCost}")
        }

        Button(
            onClick = { viewModel.activateAutoClicker() },
            modifier = Modifier.padding(16.dp),
            enabled = !viewModel.autoClickerEnabled && viewModel.gold >= viewModel.autoClickerCost
        ) {
            Text("Auto-Clicker - Cost: ${viewModel.autoClickerCost}")
        }

        Button(
            onClick = { viewModel.prestige() },
            modifier = Modifier.padding(16.dp),
            enabled = viewModel.gold >= 10000
        ) {
            Text("Prestige (Reset for x${viewModel.prestigeBonus + 1} Bonus) - Cost: 10,000 Gold")
        }

        Text(
            text = "Achievements:",
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 16.dp)
        )
        viewModel.achievements.forEach { achievement ->
            Text(
                text = "- $achievement",
                fontSize = 16.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}
