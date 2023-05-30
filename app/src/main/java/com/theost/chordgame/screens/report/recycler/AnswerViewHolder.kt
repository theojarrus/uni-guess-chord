package com.theost.chordgame.screens.report.recycler

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.theost.chordgame.R
import com.theost.chordgame.databinding.ItemAnswerBinding
import com.theost.chordgame.model.GameAnswer

class AnswerViewHolder(private val binding: ItemAnswerBinding) : ViewHolder(binding.root) {

    fun bind(item: GameAnswer) = with(binding) {
        userAnswer.text = item.userAnswer
        correctAnswer.text = item.correctAnswer
        correct.apply {
            if (item.isCorrect()) {
                setTextColor(context.getColor(R.color.purple_500))
                setText(R.string.report_correct)
            } else {
                setText(R.string.report_incorrect)
            }
        }
    }
}