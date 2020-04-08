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

    //private lateinit var country_name: String
    /*var cases: String? = null
    var deaths: String? = null
    var total_recovered: String? = null
    var new_deaths: String? = null
    var new_cases: String? = null
    var serious_critical: String? = null
    var active_cases: String? = null
    var total_cases_per_1m_population: String? = null*/


    companion object {
        const val TABLE_NAME = "countries"
    }
}