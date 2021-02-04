package ru.melart.example.translaterapplication.respositories.db.dao

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Observable
import ru.melart.example.translaterapplication.respositories.db.entities.Word

@Dao
interface WordDao {
    @Query("SELECT * FROM word")
    fun getWord(): Observable<List<Word>>

    @Query("SELECT * FROM word WHERE value =:value LIMIT 1 ")
    fun getWordByValue(value: String): Observable<Word?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWord(word: Word): Completable

    @Update
    fun updateWord(word: Word): Completable
}