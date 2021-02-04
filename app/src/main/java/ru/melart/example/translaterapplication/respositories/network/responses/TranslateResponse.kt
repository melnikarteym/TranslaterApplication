package ru.melart.example.translaterapplication.respositories.network.responses

import com.google.gson.annotations.SerializedName

class TranslateResponse(
    @SerializedName("data")
    val result: String
)