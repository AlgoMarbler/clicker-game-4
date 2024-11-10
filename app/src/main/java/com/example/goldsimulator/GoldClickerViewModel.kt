package com.example.goldsimulator

import GoldClickerViewState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

class GoldClickerViewModel : ViewModel() {
    var viewState by mutableStateOf(
        GoldClickerViewState(
            gold = 0,
            goldInformation = GoldClickerViewState.GoldValues(
                goldPerClick = 1,
                upgradeCost = 10,
                autoClickerCost = 100,
                prestigeBonus = 1,
            ),
            autoClickerEnabled = false,
            achievements = listOf(),
            autoClickerCost = 100,
            goldPerClick = 1,
            prestigeBonus = 1,
            upgradeCost = 50,
        )
    )

    init {
        startAutoClicker()
    }

    fun onGoldClick() {
        viewState = viewState.copy(
            gold = viewState.gold + viewState.goldInformation.goldPerClick * viewState.goldInformation.prestigeBonus
        )
        checkAchievements()
    }

    fun upgradeGoldPerClick() {
        if (viewState.gold >= viewState.goldInformation.upgradeCost) {

            if(viewState.upgradeCost < 85){
                viewState = viewState.copy(
                    gold = viewState.gold - viewState.goldInformation.upgradeCost,
                    goldInformation = viewState.goldInformation.copy(
                        goldPerClick = viewState.goldInformation.goldPerClick + 1,
                        upgradeCost = viewState.goldInformation.upgradeCost * 4 - 15,
                    )
                )
                checkAchievements()
            }else{
                viewState = viewState.copy(
                    gold = viewState.gold - viewState.goldInformation.upgradeCost,
                    goldInformation = viewState.goldInformation.copy(
                        goldPerClick = viewState.goldInformation.goldPerClick + 1,
                        upgradeCost = viewState.goldInformation.upgradeCost * 3 - 120,
                    )
                )
            }

        }
    }

    fun activateAutoClicker() {
        if (!viewState.autoClickerEnabled && viewState.gold >= viewState.goldInformation.autoClickerCost) {
            viewState = viewState.copy(
                gold = viewState.gold - viewState.goldInformation.autoClickerCost,
                autoClickerEnabled = true,
                goldInformation = viewState.goldInformation.copy(
                    autoClickerCost = viewState.goldInformation.autoClickerCost * 250000
                )
            )
            checkAchievements()
        }
    }

    fun prestige() {
        if (viewState.gold >= 10000) {
            viewState = GoldClickerViewState(
                gold = 0,
                goldInformation = GoldClickerViewState.GoldValues(
                    goldPerClick = 1,
                    upgradeCost = 10,
                    autoClickerCost = 100,
                    prestigeBonus = 1,
                ),
                autoClickerEnabled = false,
                achievements = listOf(),
                autoClickerCost = 100,
                goldPerClick = 1,
                prestigeBonus = 1,
                upgradeCost = 50,
            )
        }
    }

    private fun startAutoClicker() {
        viewModelScope.launch {
            while (true) {
                if (viewState.autoClickerEnabled) {
                    viewState = viewState.copy(gold = viewState.gold + viewState.goldInformation.goldPerClick)
                }
                delay(300)
            }
        }
    }

    private fun checkAchievements() {
        val newAchievements = mutableListOf<String>()
        if (viewState.gold >= 1) newAchievements.add("The beginning (Have 1 gold)")
        if (viewState.gold >= 100) newAchievements.add("Tiny steps (Have 100 gold)")
        if (viewState.gold >= 5000) newAchievements.add("Enrichment (Have 5000 gold)")
        if (viewState.gold >= 200000) newAchievements.add("Infinite gold glitch? (Have 200K gold)")
        if (viewState.gold >= 10000000) newAchievements.add("It's time to stop playing. (Have 10M gold)")

        viewState = viewState.copy(achievements = newAchievements.distinct())
    }
}
