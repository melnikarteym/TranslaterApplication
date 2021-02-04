package ru.melart.example.translaterapplication.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface CreatorView : MvpView {
    fun showLoading()
    fun showSuccess()
    fun showError(throwable: Throwable)
}