package com.theost.chordgame.model

import java.time.LocalDateTime

data class GameData(
    val score: Int? = null,
    val startTime: LocalDateTime? = null,
    val endTime: LocalDateTime? = null,
    val answers: MutableList<GameAnswer> = mutableListOf()
)