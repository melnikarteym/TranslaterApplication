package ru.melart.example.translaterapplication.respositories.db.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "word")
data class Word(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val value: String,
    val translate: String
) : Parcelable