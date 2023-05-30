package com.theost.chordgame.model

data class GamePreferences(
    val instrument: GameInstrument? = null,
    val difficulty: GameDifficulty? = null,
    val rounds: Int? = null
) {
    fun isFilled(): Boolean = instrument != null && difficulty != null && rounds != null
}