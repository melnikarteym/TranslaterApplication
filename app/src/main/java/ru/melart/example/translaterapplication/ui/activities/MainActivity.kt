package ru.melart.example.translaterapplication.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.respositories.db.entities.Word
import ru.melart.example.translaterapplication.ui.fragments.CreatorFragment
import ru.melart.example.translaterapplication.ui.fragments.DetailFragment
import ru.melart.example.translaterapplication.ui.fragments.ListFragment

class MainActivity : AppCompatActivity(), OnItemClickListener, OnCreateButtonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            initFragments()
        }


    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, ListFragment())
            .commit()
    }

    override fun onItemClick(word: Word) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DetailFragment.newInstance(word))
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateButtonClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, CreatorFragment())
            .addToBackStack(null)
            .commit()
    }
}