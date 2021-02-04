package ru.melart.example.translaterapplication.respositories.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RequestManager {

    private val client = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .writeTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .connectTimeout(TIMEOUT_IN_SECOND, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    val service: WordService = retrofit.create(WordService::class.java)

    companion object {
        private const val TIMEOUT_IN_SECOND = 5L
        private const val BASE_URL = "https://fasttranslator.herokuapp.com/api/v1/"
    }
}