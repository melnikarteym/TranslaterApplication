package ru.melart.example.translaterapplication.respositories.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.melart.example.translaterapplication.respositories.db.dao.WordDao
import ru.melart.example.translaterapplication.respositories.db.entities.Word

@Database(entities = [Word::class], version = 1, exportSchema = false)
abstract class WordDatabase : RoomDatabase() {
    abstract fun wordDao(): WordDao
}