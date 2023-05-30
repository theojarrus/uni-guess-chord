package com.theost.chordgame.model

import com.theost.chordgame.model.GamePreference.Rounds
import com.theost.chordgame.model.GamePreference.Difficulty
import com.theost.chordgame.model.GamePreference.Instrument

sealed class GamePreferenceInfo(val name: String, val variants: List<String>) {

    object Rounds : GamePreferenceInfo(
        name = "Количество раундов",
        variants = listOf("5", "10", "15")
    ) {
        override fun value(item: String): GamePreference {
            return Rounds(item.toInt())
        }
    }

    object Difficulty : GamePreferenceInfo(
        name = "Сложность",
        variants = GameDifficulty.values().map(GameDifficulty::value)
    ) {
        override fun value(item: String): GamePreference {
            return Difficulty(requireNotNull(GameDifficulty.values().find { it.value == item }))
        }
    }

    object Instrument : GamePreferenceInfo(
        name = "Инструмент",
        variants = GameInstrument.values().map(GameInstrument::value)
    ) {
        override fun value(item: String): GamePreference {
            return Instrument(requireNotNull(GameInstrument.values().find { it.value == item }))
        }
    }

    abstract fun value(item: String): GamePreference
}