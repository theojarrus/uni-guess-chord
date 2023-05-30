package com.theost.chordgame.screens.result

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.format.DateUtils
import android.view.View
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.theost.chordgame.R
import com.theost.chordgame.databinding.FragmentResultBinding
import com.theost.chordgame.presentation.GameViewModel
import com.theost.chordgame.screens.result.ResultFragmentDirections.Companion.actionResultToMenu
import com.theost.chordgame.screens.result.ResultFragmentDirections.Companion.actionResultToReport
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.util.*

class ResultFragment : Fragment(R.layout.fragment_result) {

    private val viewModel: GameViewModel by activityViewModels()
    private val binding: FragmentResultBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.report.setOnClickListener { findNavController().navigate(actionResultToReport()) }
        binding.menu.setOnClickListener {
            viewModel.reset()
            findNavController().navigate(actionResultToMenu())
        }
        viewModel.data.value?.let { data ->
            binding.score.text = getFormattedString(
                label = R.string.result_score,
                value = data.score.toString()
            )
            binding.time.text = getFormattedString(
                label = R.string.result_time,
                value = getFormattedDate(
                    startTime = data.startTime ?: LocalDateTime.now(),
                    endTime = data.endTime ?: LocalDateTime.now()
                )
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getFormattedDate(startTime: LocalDateTime, endTime: LocalDateTime): String {
        return SimpleDateFormat("mm:ss").format(Duration.between(startTime, endTime).toMillis())
    }

    private fun getFormattedString(@StringRes label: Int, value: String): String {
        return String.format(requireContext().getString(label), value)
    }
}