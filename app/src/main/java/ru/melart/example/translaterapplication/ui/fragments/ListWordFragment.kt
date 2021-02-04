package ru.melart.example.translaterapplication.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_list_word.*
import moxy.MvpAppCompatFragment
import moxy.presenter.InjectPresenter
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.presenters.ListWordPresenter
import ru.melart.example.translaterapplication.respositories.db.entities.Word
import ru.melart.example.translaterapplication.ui.activities.OnCreateButtonClickListener
import ru.melart.example.translaterapplication.ui.activities.OnItemClickListener
import ru.melart.example.translaterapplication.ui.adapters.WordAdapter
import ru.melart.example.translaterapplication.view.ListWordView

class ListWordFragment : MvpAppCompatFragment(R.layout.fragment_list_word), ListWordView {

    private var onItemCLickListener: OnItemClickListener? = null
    private var onCreateButtonClickListener: OnCreateButtonClickListener? = null

    @InjectPresenter
    lateinit var presenter: ListWordPresenter

    private lateinit var wordAdapter: WordAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClickListener) {
            onItemCLickListener = context
        }

        if (context is OnCreateButtonClickListener){
            onCreateButtonClickListener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        presenter.updateData()
        createButton.setOnClickListener { onCreateButtonClickListener?.onCreateButtonClicked() }
    }

    override fun onDetach() {
        super.onDetach()
        onItemCLickListener = null
        onCreateButtonClickListener = null
    }

    private fun initRecyclerView() {
        wordAdapter = WordAdapter { id -> onItemCLickListener?.onItemClick(id) }
        with(recyclerWords) {
            adapter = wordAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun showLoading() {
        recyclerWords.isVisible = false
        emptyListView.isVisible = false
        repeatButton.isVisible = false
        wordsProgressBar.isVisible = true
    }

    override fun showError(throwable: Throwable) {
        Snackbar.make(createButton, getString(R.string.network_error), Snackbar.LENGTH_SHORT).show()
        repeatButton.isVisible = true
    }

    override fun showResult(words: List<Word>) {
        repeatButton.isVisible = false
        wordsProgressBar.isVisible = false
        recyclerWords.isVisible = words.isNotEmpty()
        emptyListView.isVisible = words.isEmpty()
        wordAdapter.submitList(words)
    }
}