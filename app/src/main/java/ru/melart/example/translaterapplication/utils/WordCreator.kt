package ru.melart.example.translaterapplication.utils

import ru.melart.example.translaterapplication.respositories.db.entities.Word

object WordCreator {
    private var id: Long = 0

    fun createWordByValueAndTranslate(value: String, translate: String): Word {
        return Word(id++, value, translate)
    }
}