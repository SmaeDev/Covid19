package com.smaedev.covi19.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smaedev.covi19.AppDatabase
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private val countryDao = AppDatabase.database.countryDao()
    private val countryRepository: CountryRepository = CountryRepository(countryDao)

    val allCountries: LiveData<List<Country>> = countryRepository.allCountries

    fun getCountryByName(countryName: String?): LiveData<List<Country>> = countryRepository.getCountry(countryName)

    fun getCountries() = countryRepository.fetchCountries()

    /*fun insertCountry(country : Country) = viewModelScope.launch {
        countryRepository.insert(country)
    }

    fun insertListCountry(countries : List<Country>) = viewModelScope.launch {
        countryRepository.insertAll(countries)
    }*/

}