package com.theost.chordgame.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.theost.chordgame.model.GameAnswer
import com.theost.chordgame.model.GameData
import com.theost.chordgame.model.GamePreference
import com.theost.chordgame.model.GamePreference.*
import com.theost.chordgame.model.GamePreferences
import java.time.LocalDateTime
import java.util.*

class GameViewModel : ViewModel() {

    private val _preferences: MutableLiveData<GamePreferences> = MutableLiveData(GamePreferences())
    val preferences: LiveData<GamePreferences> = _preferences

    private val _data: MutableLiveData<GameData> = MutableLiveData(GameData())
    val data: LiveData<GameData> = _data

    fun savePreference(preference: GamePreference) {
        preferences {
            when (preference) {
                is Rounds -> copy(rounds = preference.value)
                is Difficulty -> copy(difficulty = preference.value)
                is Instrument -> copy(instrument = preference.value)
            }
        }
    }

    fun saveAnswer(userAnswer: String, correctAnswer: String) {
        data { copy(answers = answers.apply { add(GameAnswer(userAnswer, correctAnswer)) }) }
    }

    fun changeScore(points: Int) {
        data {
            val score = (score ?: 0).plus(points)
            copy(score = if (score > 0) score else 0)
        }
    }

    fun saveStartTime() {
        data { copy(startTime = LocalDateTime.now()) }
    }

    fun saveEndTime() {
        data { copy(endTime = LocalDateTime.now()) }
    }

    fun reset() {
        _preferences.value = GamePreferences()
        _data.value = GameData()
    }

    private fun preferences(update: GamePreferences.() -> GamePreferences) {
        _preferences.value = _preferences.value?.run { update(this) }
    }

    private fun data(update: GameData.() -> GameData) {
        _data.value = _data.value?.run { update(this) }
    }
}