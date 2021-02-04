package ru.melart.example.translaterapplication.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_detail_word.*
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.respositories.db.entities.Word
import java.lang.IllegalStateException

class DetailFragment : Fragment(R.layout.fragment_detail_word) {

    private lateinit var word: Word

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        word = requireArguments().getParcelable(KEY_ID) as? Word
            ?: throw IllegalStateException("You don't use contract of this Fragment.")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordValue.text = word.value
        wordTranslate.text = word.translate
    }

    companion object {
        private const val KEY_ID = "DetailFragment.KEY_ID"

        fun newInstance(word: Word): DetailFragment {
            return DetailFragment().apply {
                arguments = bundleOf(KEY_ID to word)
            }
        }
    }
}