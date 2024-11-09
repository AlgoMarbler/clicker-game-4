package com.example.goldsimulator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GoldClickerViewModel : ViewModel() {
    var gold by mutableStateOf(0L)
        private set
    var goldPerClick by mutableStateOf(1L)
        private set
    var upgradeCost by mutableStateOf(50L)
        private set
    var autoClickerCost by mutableStateOf(100L)
        private set
    var prestigeBonus by mutableStateOf(1L)
        private set
    var autoClickerEnabled by mutableStateOf(false)
        private set
    val achievements = mutableStateListOf<String>()

    init {
        startAutoClicker()
    }

    fun onGoldClick() {
        gold += goldPerClick * prestigeBonus
        checkAchievements()
    }

    fun upgradeGoldPerClick() {
        if (gold >= upgradeCost) {
            gold -= upgradeCost
            goldPerClick += 1
            upgradeCost = (upgradeCost * 1.5).toLong()
            checkAchievements()
        }
    }

    fun activateAutoClicker() {
        if (!autoClickerEnabled && gold >= autoClickerCost) {
            gold -= autoClickerCost
            autoClickerEnabled = true
            autoClickerCost = (autoClickerCost * 2).toLong()
            checkAchievements()
        }
    }

    fun prestige() {
        if (gold >= 10000) {
            gold = 0
            goldPerClick = 1
            upgradeCost = 50
            prestigeBonus += 1
            autoClickerEnabled = false
            autoClickerCost = 100
            achievements.clear()
        }
    }

    private fun startAutoClicker() {
        viewModelScope.launch {
            while (true) {
                if (autoClickerEnabled) {
                    gold += goldPerClick
                }
                delay(1000)
            }
        }
    }

    private fun checkAchievements() {
        val newAchievements = mutableListOf<String>()
        if (gold >= 1000) newAchievements.add("Gold Hoarder: 1000 Gold!")
        if (goldPerClick >= 10) newAchievements.add("Power Clicker: 10 Gold per Click!")
        if (autoClickerEnabled) newAchievements.add("Auto-clicker Activated!")
        achievements.clear()
        achievements.addAll(newAchievements.distinct())
    }
}
