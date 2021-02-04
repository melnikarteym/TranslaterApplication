package ru.melart.example.translaterapplication.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.melart.example.translaterapplication.respositories.db.entities.Word

@StateStrategyType(AddToEndSingleStrategy::class)
interface ListWordView : MvpView {
    fun showLoading()
    fun showError(throwable: Throwable)
    fun showResult(words: List<Word>)
}