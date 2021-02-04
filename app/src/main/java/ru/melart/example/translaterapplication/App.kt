package ru.melart.example.translaterapplication

import android.app.Application
import ru.melart.example.translaterapplication.respositories.db.WordRepository

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        WordRepository.initialize(this)
    }
}