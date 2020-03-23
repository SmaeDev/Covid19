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

    suspend fun insertCountry(country:String, death:Int, ill:Int, total:Int ) {
        //val country = Country(country,death,ill,total)
        //countryDao.insert(country)
    }
    val allCountries: LiveData<List<Country>> = countryDao.getAlphabetizedCountries()
}