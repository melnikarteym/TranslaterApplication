package ru.melart.example.translaterapplication.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.melart.example.translaterapplication.respositories.db.WordRepository
import ru.melart.example.translaterapplication.utils.WordCreator

class CreatorViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val repository = WordRepository.get()

    fun addWord(value: String) {
        val disposable = repository.insertWord(
            WordCreator.createWordByValueAndTranslate(
                value,
                "default translate"
            )
        )
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

            }, {

            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
    companion object{
        private const val TAG = "CreatorViewModel"
    }
}