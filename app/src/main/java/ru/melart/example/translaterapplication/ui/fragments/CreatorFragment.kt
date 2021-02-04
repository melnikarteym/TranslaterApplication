package ru.melart.example.translaterapplication.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_creator.*
import ru.melart.example.translaterapplication.R
import ru.melart.example.translaterapplication.viewmodels.CreatorViewModel

class CreatorFragment : Fragment(R.layout.fragment_creator){

    private val viewModel: CreatorViewModel by lazy { ViewModelProvider(this).get(CreatorViewModel::class.java) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createButton.setOnClickListener {
            viewModel.addWord(inputWord.text.toString())
        }
    }
}