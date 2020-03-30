package com.smaedev.covi19.db

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = Country.TABLE_NAME)
class Country(
    @PrimaryKey @NonNull
    val country_name: String,
    val cases: String,
    val deaths: String,
    val total_recovered: String,
    val new_deaths: String,
    val new_cases: String,
    val serious_critical: String,
    val active_cases: String,
    val total_cases_per_1m_population: String

) {
    companion object {
    const val TABLE_NAME = "countries"
    }

}