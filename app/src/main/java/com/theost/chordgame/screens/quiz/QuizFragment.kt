package com.theost.chordgame.screens.quiz

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.theost.chordgame.R
import com.theost.chordgame.databinding.FragmentQuizBinding
import com.theost.chordgame.model.GameDifficulty
import com.theost.chordgame.model.GameInstrument
import com.theost.chordgame.model.GameInstrument.*
import com.theost.chordgame.model.GamePreferences
import com.theost.chordgame.presentation.GameViewModel
import com.theost.chordgame.screens.preference.recycler.PreferenceAdapter
import com.theost.chordgame.screens.quiz.QuizFragmentDirections.Companion.actionQuizToResult
import com.theost.chordgame.widget.ChordGenerator
import java.util.*
import kotlin.math.sqrt
import kotlin.random.Random

class QuizFragment : Fragment(R.layout.fragment_quiz) {

    private val viewModel: GameViewModel by activityViewModels()
    private val binding: FragmentQuizBinding by viewBinding()

    private val adapter: PreferenceAdapter by lazy {
        PreferenceAdapter() { item ->
            val isCorrect = item == answer
            viewModel.saveAnswer(item, answer)
            viewModel.changeScore(if (isCorrect) 10 else -5)
            if (round < (rounds ?: 0)) {
                round += 1
                nextChord()
            } else {
                viewModel.saveEndTime()
                findNavController().navigate(actionQuizToResult())
            }
        }
    }

    private val generator: ChordGenerator by lazy {
        ChordGenerator(
            requireActivity(),
            binding.image
        )
    }

    private val keys: Array<String> by lazy {
        resources.getStringArray(R.array.array_musical_keys)
    }

    private val chords: Array<String> by lazy {
        resources.getStringArray(R.array.array_musical_chords)
    }

    private val preferences: GamePreferences?
        get() = viewModel.preferences.value

    private val instrument: GameInstrument?
        get() = preferences?.instrument

    private val difficulty: GameDifficulty?
        get() = preferences?.difficulty

    private val rounds: Int?
        get() = preferences?.rounds

    private var round: Int = 1
    private var answer: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) viewModel.saveStartTime()
        val layoutManager = binding.recycler.layoutManager as? GridLayoutManager
        layoutManager?.spanCount = sqrt(difficulty?.count?.toFloat() ?: 0f).toInt()
        binding.recycler.adapter = adapter
        nextChord()
    }

    private fun nextChord() {
        binding.image.removeAllViews()
        val random = Random(Date().time)
        val correctKey = random.nextInt(keys.size)
        val correctChord = random.nextInt(chords.size)
        answer = keys[correctKey] + chords[correctChord]
        generator.generateChord(
            answer,
            getInstrument(instrument ?: GUITAR)
        )
        val items = mutableListOf<String>()
        while (items.size < (difficulty?.count ?: 0) - 1) {
            val key = random.nextInt(keys.size)
            val chord = random.nextInt(chords.size)
            if (key != correctKey || chord != correctChord) {
                items.add(keys[key] + chords[chord])
            }
        }
        items.add(random.nextInt(items.size) + 1, answer)
        adapter.setItems(items)
    }

    private fun getInstrument(instrument: GameInstrument): Int {
        return when (instrument) {
            GUITAR -> R.string.guitar
            PIANO -> R.string.piano
            UKULELE -> R.string.ukulele
        }
    }
}