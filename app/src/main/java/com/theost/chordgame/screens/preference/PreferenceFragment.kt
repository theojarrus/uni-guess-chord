package com.theost.chordgame.screens.preference

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.theost.chordgame.R
import com.theost.chordgame.databinding.FragmentPreferenceBinding
import com.theost.chordgame.model.GamePreferenceInfo
import com.theost.chordgame.model.GamePreferenceInfo.*
import com.theost.chordgame.model.GamePreferences
import com.theost.chordgame.presentation.GameViewModel
import com.theost.chordgame.screens.preference.PreferenceFragmentDirections.Companion.actionPreference
import com.theost.chordgame.screens.preference.PreferenceFragmentDirections.Companion.actionPreferenceToQuiz
import com.theost.chordgame.screens.preference.recycler.PreferenceAdapter

class PreferenceFragment : Fragment(R.layout.fragment_preference) {

    private val viewModel: GameViewModel by activityViewModels()
    private val binding: FragmentPreferenceBinding by viewBinding()

    private val preferenceData: GamePreferences?
        get() = viewModel.preferences.value

    private val preferenceInfo: GamePreferenceInfo by lazy {
        when {
            preferenceData?.instrument == null -> Instrument
            preferenceData?.difficulty == null -> Difficulty
            preferenceData?.rounds == null -> Rounds
            else -> throw Error("unreachable state")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.name.text = preferenceInfo.name
        binding.recycler.adapter = PreferenceAdapter(preferenceInfo.variants) { item ->
            viewModel.savePreference(preferenceInfo.value(item))
            val isFilled = preferenceData?.isFilled() == true
            val direction = if (isFilled) actionPreferenceToQuiz() else actionPreference()
            findNavController().navigate(direction)
        }
    }
}