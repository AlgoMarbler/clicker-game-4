data class GoldClickerViewState(
    val gold: Int,
    val goldInformation: GoldValues,
    val autoClickerEnabled: Boolean,
    val achievements: List<String>,

    val goldPerClick: Int,
    val upgradeCost: Int,
    val autoClickerCost: Int,
    val prestigeBonus: Int,
) {
    val goldDisplay = "Gold: $gold"
    val goldPerClickDisplay = "Gold per Click: ${goldInformation.goldPerClick}"
    val prestigeBonusDisplay = "Prestige Bonus: ${goldInformation.prestigeBonus}"
    val goldButtonText = "Click me for ${goldInformation.goldPerClick} gold!"
    val upgradeButtonText = "Upgrade: +1 gold per click - ${goldInformation.upgradeCost} gold"
    val autoClickerText = "Enable auto clicker: ${goldInformation.autoClickerCost}"
    val prestigeButtonText = "Prestige for ${goldInformation.prestigeBonus} + 1 bonus multiplier - 10,000 gold"
    val achievementsHeader = "Achievements:"

    data class GoldValues(
        val goldPerClick: Int,
        val upgradeCost: Int,
        val autoClickerCost: Int,
        val prestigeBonus: Int,
    )
}
