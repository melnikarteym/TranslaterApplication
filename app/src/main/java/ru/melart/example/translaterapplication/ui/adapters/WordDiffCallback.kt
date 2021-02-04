package ru.melart.example.translaterapplication.ui.adapters

import androidx.recyclerview.widget.DiffUtil
import ru.melart.example.translaterapplication.respositories.db.entities.Word

class WordDiffCallback : DiffUtil.ItemCallback<Word>() {
    override fun areItemsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Word, newItem: Word): Boolean = oldItem == newItem
}