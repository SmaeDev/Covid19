package com.smaedev.covid19.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smaedev.covid19.AppDatabase
import com.smaedev.covid19.db.Country
import com.smaedev.covid19.repository.CountryRepository

class CountryViewModel : ViewModel() {

    private val countryDao = AppDatabase.database.countryDao()

    //val countryDao = AppDatabase.getDatabase(MainActivity.mainContext(), viewModelScope).countryDao()
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