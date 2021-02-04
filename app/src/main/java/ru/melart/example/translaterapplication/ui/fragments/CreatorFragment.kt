package ru.melart.example.translaterapplication.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_creator.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.presenters.CreatorPresenter
import ru.melart.example.translaterapplication.view.CreatorView
import java.util.*

class CreatorFragment : MvpAppCompatFragment(R.layout.fragment_creator), CreatorView {

    @InjectPresenter
    lateinit var presenter: CreatorPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createButton.setOnClickListener {
            presenter.addWord(inputWord.text.toString().trim().toLowerCase(Locale.ROOT).capitalize(Locale.ROOT))
            hideKeyboard()
        }
    }

    override fun showLoading() {
        createButton.isVisible = false
        loadingProgressBar.isVisible = true
    }

    override fun showSuccess() {
        loadingProgressBar.isVisible = false
        Snackbar.make(inputWord, getString(R.string.success_added_word), Snackbar.LENGTH_SHORT).show()
        requireActivity().onBackPressed()
    }

    override fun showError(throwable: Throwable) {
        Snackbar.make(inputWord, getString(R.string.network_error), Snackbar.LENGTH_SHORT).show()
        loadingProgressBar.isVisible = false
        createButton.isVisible = true
    }

    private fun hideKeyboard() {
        val inputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(inputWord.windowToken, 0)
    }
}