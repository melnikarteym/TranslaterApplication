package ru.melart.example.translaterapplication.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ru.melart.example.translaterapplication.respositories.db.WordRepository
import ru.melart.example.translaterapplication.respositories.db.entities.Word

class ListViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val repository = WordRepository.get()
    private val mutableWords = MutableLiveData<List<Word>>()
    val words: LiveData<List<Word>> = mutableWords

    init {
        val disposable = repository.getWords().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ listOfWord ->
                mutableWords.value = listOfWord
            }, {  })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}