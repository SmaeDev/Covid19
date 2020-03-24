package com.smaedev.covi19.Repository

import androidx.lifecycle.LiveData
import com.smaedev.covi19.DB.Country
import com.smaedev.covi19.DB.CountryDao

class CountryRepository(private val countryDao: CountryDao) {

    fun isValidCountry(countryname: String?): Boolean {
        val country: Country? = countryDao.getCountries(countryname)
        return country?.country_name.equals(countryname)
    }

    fun countryExist(countryname: String?): Boolean {
        val country: Country? = countryDao.getCountries(countryname)
        return if (country != null) true else false
    }

    /*suspend fun insertCountry(country:String, cases:String, deaths:String, total_recovered : String, new_deaths:String,
                              new_cases:String, serious_critical : String, active_cases : String,total_cases_per_1m_population:String) {
        val country = Country(country,cases, deaths, total_recovered,new_deaths,new_cases,serious_critical,active_cases,
            total_cases_per_1m_population)
        countryDao.insert(country)
    }*/

    suspend fun insert(country: Country) {
        countryDao.insert(country)
    }
    val allCountries: LiveData<List<Country>> = countryDao.getAlphabetizedCountries()
}