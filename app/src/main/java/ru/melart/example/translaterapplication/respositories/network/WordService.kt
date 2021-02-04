package ru.melart.example.translaterapplication.respositories.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ru.melart.example.translaterapplication.respositories.network.responses.TranslateResponse

interface WordService {

    @GET("text/to/text")
    fun getTranslate(@Query("source") source: String, @Query("lang") lang: String = DEFAULT_LANGUAGE): Single<TranslateResponse>

    companion object {
        private const val DEFAULT_LANGUAGE = "ru-en"
    }
}