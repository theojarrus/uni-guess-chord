package com.theost.chordgame.screens.preference.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.theost.chordgame.databinding.ItemPreferenceBinding

class PreferenceAdapter(
    private var items: List<String> = emptyList(),
    private val listener: (String) -> Unit
) : Adapter<PreferenceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PreferenceViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPreferenceBinding.inflate(inflater, parent, false)
        return PreferenceViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: PreferenceViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(items: List<String>) {
        this.items = items.toList()
        notifyDataSetChanged()
    }
}