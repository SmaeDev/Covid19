package com.smaedev.covi19.DB

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

    /*val countryDeath: Int? = null
    val countryIll: Int? = null
    val countryCorona: Int? = null*/

    companion object {
    const val TABLE_NAME = "countries"
    }

}