package com.theost.chordgame.model


sealed class GamePreference {

    class Rounds(val value: Int) : GamePreference()
    class Difficulty(val value: GameDifficulty) : GamePreference()
    class Instrument(val value: GameInstrument) : GamePreference()
}