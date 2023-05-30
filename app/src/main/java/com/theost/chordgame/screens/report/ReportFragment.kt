package com.theost.chordgame.screens.report

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.theost.chordgame.R
import com.theost.chordgame.databinding.FragmentReportBinding
import com.theost.chordgame.presentation.GameViewModel
import com.theost.chordgame.screens.report.recycler.AnswerAdapter

class ReportFragment : Fragment(R.layout.fragment_report) {

    private val viewModel: GameViewModel by activityViewModels()
    private val binding: FragmentReportBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        recycler.adapter = AnswerAdapter(viewModel.data.value?.answers.orEmpty())
        binding.back.setOnClickListener { findNavController().navigateUp() }
    }
}