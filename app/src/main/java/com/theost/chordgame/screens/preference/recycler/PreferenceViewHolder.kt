package com.theost.chordgame.screens.preference.recycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.theost.chordgame.databinding.ItemPreferenceBinding

class PreferenceViewHolder(
    private val binding: ItemPreferenceBinding,
    private val listener: (String) -> Unit
) : ViewHolder(binding.root) {

    fun bind(item: String) = with(binding.root) {
        setOnClickListener { listener(item) }
        text = item
    }
}