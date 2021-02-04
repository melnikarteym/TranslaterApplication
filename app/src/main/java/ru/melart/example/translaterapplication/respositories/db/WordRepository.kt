package ru.melart.example.translaterapplication.respositories.db

import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Observable
import ru.melart.example.translaterapplication.respositories.db.entities.Word
import java.lang.IllegalStateException

class WordRepository private constructor(context: Context) {

    private val database =
        Room.databaseBuilder(context, WordDatabase::class.java, DATABASE_NAME).build()

    private val wordDao = database.wordDao()

    fun getWords(): Observable<List<Word>> {
        return wordDao.getWord()
    }

    fun insertWord(word: Word): Completable {
        return wordDao.insertWord(word)
    }

    companion object {
        private const val DATABASE_NAME = "word_database"

        private var INSTANCE: WordRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = WordRepository(context)
            }
        }

        fun get(): WordRepository{
            return INSTANCE ?: throw IllegalStateException("WordRepository must be initialized")
        }
    }
}