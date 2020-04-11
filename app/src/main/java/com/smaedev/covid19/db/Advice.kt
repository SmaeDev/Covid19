package com.smaedev.covid19.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Advice.TABLE_NAME)
class Advice(
    @PrimaryKey @NonNull
    val idAdvice: String,
    val title: String,
    val image: String,
    val description: String

) {
    companion object {
        const val TABLE_NAME = "advices"
    }
}