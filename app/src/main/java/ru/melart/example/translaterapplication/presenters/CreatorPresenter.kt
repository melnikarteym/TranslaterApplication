package ru.melart.example.translaterapplication.presenters

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.melart.example.translaterapplication.respositories.db.WordRepository
import ru.melart.example.translaterapplication.utils.WordCreator
import ru.melart.example.translaterapplication.view.CreatorView
import java.util.concurrent.TimeUnit

@InjectViewState
class CreatorPresenter : MvpPresenter<CreatorView>() {
    private val repository = WordRepository.get()

    private val compositeDisposable = CompositeDisposable()

    fun addWord(value: String) {
        val disposable = repository.insertWord(
            WordCreator.createWordByValueAndTranslate(
                value,
                "default translate"
            )
        )
            .subscribeOn(Schedulers.io())
            .doOnSubscribe { viewState.showLoading() }
            .delay(2, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d(TAG, "addWord: ")
                viewState.showSuccess()
            }, { error ->
                Log.d(TAG, "addWord: ${error.message}")
                viewState.showError(error)
            })

        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }

    companion object{
        private const val TAG = "CreatorPresenter"
    }
}