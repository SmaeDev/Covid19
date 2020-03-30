package com.smaedev.covi19.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smaedev.covi19.AppDatabase
import com.smaedev.covi19.MainActivity
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private val countryRepository: CountryRepository
    val allCountries: LiveData<List<Country>>

    init {
        val countryDao = AppDatabase.getDatabase(MainActivity.mainActivityContext(), viewModelScope).countryDao()
        countryRepository = CountryRepository(countryDao)
        allCountries = countryRepository.allCountries
    }

    fun insertCountry(country : Country) = viewModelScope.launch {
        countryRepository.insert(country)
    }

    fun insertListCountry(countries : List<Country>) = viewModelScope.launch {
        countryRepository.insertAll(countries)
    }

    fun getCountries() {
        countryRepository.fetchCountries()
    }

}

interface OnItemClickListener{

    fun onCountryClicked(country: Country)

}