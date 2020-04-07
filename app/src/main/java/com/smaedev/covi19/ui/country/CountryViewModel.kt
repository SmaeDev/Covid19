package com.smaedev.covi19.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smaedev.covi19.AppDatabase
import com.smaedev.covi19.MainActivity
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.repository.CountryRepository

class CountryViewModel : ViewModel() {

    private val countryDao = AppDatabase.base.countryDao()

    private val countryRepository: CountryRepository = CountryRepository(countryDao)

    val allCountries: LiveData<List<Country>> = countryRepository.allCountries


    fun getCountryByName(countryName: String?): LiveData<List<Country>> = countryRepository.getCountry(countryName)


    /*fun insertCountry(country : Country) = viewModelScope.launch {
        countryRepository.insert(country)
    }

    fun insertListCountry(countries : List<Country>) = viewModelScope.launch {
        countryRepository.insertAll(countries)
    }

    fun getCountries() {
        searchCountries()
        //countryRepository.fetchCountries()
    }*/

}