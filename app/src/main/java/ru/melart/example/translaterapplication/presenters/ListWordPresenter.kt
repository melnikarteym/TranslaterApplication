package ru.melart.example.translaterapplication.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.melart.example.translaterapplication.respositories.db.WordRepository
import ru.melart.example.translaterapplication.view.ListWordView
import java.util.concurrent.TimeUnit

@InjectViewState
class ListWordPresenter : MvpPresenter<ListWordView>() {

    private val compositeDisposable = CompositeDisposable()

    private val repository = WordRepository.get()

    fun updateData() {
        val disposable = repository.getWords()
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showLoading() }
            .delay(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ words ->
                viewState.showResult(words)
            }, { error ->
                viewState.showError(error)
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}