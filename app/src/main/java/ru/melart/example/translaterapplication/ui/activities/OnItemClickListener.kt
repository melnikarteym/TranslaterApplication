package ru.melart.example.translaterapplication.ui.activities

import ru.melart.example.translaterapplication.respositories.db.entities.Word

interface OnItemClickListener {
    fun onItemClick(word: Word)
}