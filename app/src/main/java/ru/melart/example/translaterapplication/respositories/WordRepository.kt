package ru.melart.example.translaterapplication.respositories

import android.content.Context
import androidx.room.Room
import io.reactivex.Completable
import io.reactivex.Observable
import ru.melart.example.translaterapplication.respositories.db.WordDatabase
import ru.melart.example.translaterapplication.respositories.db.entities.Word
import ru.melart.example.translaterapplication.respositories.network.RequestManager
import ru.melart.example.translaterapplication.respositories.network.WordService

class WordRepository private constructor(context: Context, private val service: WordService) {

    private val database = Room.databaseBuilder(context, WordDatabase::class.java, DATABASE_NAME).build()
    private val wordDao = database.wordDao()

    fun getWords(): Observable<List<Word>> {
        return wordDao.getWord()
    }

    fun addWord(value: String): Completable {
        return service.getTranslate(value)
            .flatMapCompletable { translateFromNetwork ->
                wordDao.insertWord(Word(value, translateFromNetwork.result))
            }
    }

    companion object {
        private const val DATABASE_NAME = "word_database"
        private var INSTANCE: WordRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = WordRepository(context, RequestManager().service)
            }
        }

        fun get(): WordRepository {
            return INSTANCE ?: throw IllegalStateException("WordRepository must be initialized")
        }
    }
}