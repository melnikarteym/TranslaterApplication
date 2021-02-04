package ru.melart.example.translaterapplication.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.fragment_creator.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.presenters.CreatorPresenter
import ru.melart.example.translaterapplication.view.CreatorView

class CreatorFragment : MvpAppCompatFragment(R.layout.fragment_creator), CreatorView{

    @InjectPresenter
    lateinit var presenter: CreatorPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createButton.setOnClickListener {
            presenter.addWord(inputWord.text.toString())
        }
    }

    override fun showLoading() {
        createButton.isVisible = false
        loadingProgressBar.isVisible = true
    }

    override fun showSuccess() {
        Toast.makeText(requireContext(), getString(R.string.success_added_word), Toast.LENGTH_SHORT).show()
        requireActivity().onBackPressed()
    }

    override fun showError(throwable: Throwable) {

    }
}