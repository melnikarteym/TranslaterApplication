package ru.melart.example.translaterapplication.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_word.view.*
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.respositories.db.entities.Word

class WordAdapter(private val clickListener: (Word) -> Unit) :
    ListAdapter<Word, WordAdapter.WordViewHolder>(WordDiffCallback()) {

    class WordViewHolder(view: View, private val clickListener: (Word) -> Unit) :
        RecyclerView.ViewHolder(view) {

        fun bind(word: Word) {
            with(itemView) {
                setOnClickListener { clickListener(word) }
                wordValue.text = word.value
                wordTranslate.text = word.translate
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_word, parent, false)
        return WordViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}