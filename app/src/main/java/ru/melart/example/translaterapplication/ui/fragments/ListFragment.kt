package ru.melart.example.translaterapplication.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_word.*
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.respositories.db.entities.Word
import ru.melart.example.translaterapplication.ui.activities.OnCreateButtonClickListener
import ru.melart.example.translaterapplication.ui.activities.OnItemClickListener
import ru.melart.example.translaterapplication.ui.adapters.WordAdapter
import ru.melart.example.translaterapplication.viewmodels.ListViewModel

class ListFragment : Fragment(R.layout.fragment_list_word) {

    private var onItemCLickListener: OnItemClickListener? = null
    private var onCreateButtonClickListener: OnCreateButtonClickListener? = null

    private lateinit var wordAdapter: WordAdapter

    private val viewModel: ListViewModel by lazy { ViewModelProvider(this).get(ListViewModel::class.java) }

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
        viewModel.words.observe(viewLifecycleOwner, { updateUi(it) })
    }

    private fun updateUi(words: List<Word>) {
        recyclerWords.isVisible = words.isNotEmpty()
        emptyListView.isVisible = words.isEmpty()

        wordAdapter.submitList(words)
    }
}