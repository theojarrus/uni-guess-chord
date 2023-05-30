package com.theost.chordgame.screens.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.theost.chordgame.R
import com.theost.chordgame.databinding.FragmentMenuBinding
import com.theost.chordgame.screens.menu.MenuFragmentDirections.Companion.actionMenuToPreference

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private val binding: FragmentMenuBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.start.setOnClickListener { findNavController().navigate(actionMenuToPreference()) }
    }
}