package com.theost.chordgame.model

data class GameAnswer(
    val userAnswer: String,
    val correctAnswer: String
) {

    fun isCorrect(): Boolean = userAnswer == correctAnswer
}
