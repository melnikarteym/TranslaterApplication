package ru.melart.example.translaterapplication.ui.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.respositories.db.entities.Word
import ru.melart.example.translaterapplication.ui.fragments.CreatorFragment
import ru.melart.example.translaterapplication.ui.fragments.DetailFragment
import ru.melart.example.translaterapplication.ui.fragments.ListWordFragment

class MainActivity : AppCompatActivity(), OnItemClickListener, OnCreateButtonClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            initFragments()
        }
    }

    override fun onItemClick(word: Word) {
        hideMainFragmentAndShowAdditional(DetailFragment.newInstance(word))
    }

    override fun onCreateButtonClicked() {
        hideMainFragmentAndShowAdditional(CreatorFragment())
    }

    private fun hideMainFragmentAndShowAdditional(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .hide(supportFragmentManager.findFragmentByTag(FRAGMENT_LIST_WORD_KEY)!!)
            .add(R.id.fragmentContainer, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun initFragments() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainer, ListWordFragment(), FRAGMENT_LIST_WORD_KEY)
            .commit()
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val FRAGMENT_LIST_WORD_KEY = "MainActivity.FRAGMENT_LIST_WORD_KEY"
    }
}