package ru.melart.example.translaterapplication.presenters

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.melart.example.translaterapplication.respositories.WordRepository
import ru.melart.example.translaterapplication.view.CreatorView

@InjectViewState
class CreatorPresenter : MvpPresenter<CreatorView>() {

    private val repository = WordRepository.get()

    private val compositeDisposable = CompositeDisposable()

    fun addWord(value: String) {
        val disposable = repository.addWord(value)
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showLoading() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.showSuccess() },
                { error -> viewState.showError(error) }
            )

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}