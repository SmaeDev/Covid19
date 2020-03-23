package com.smaedev.covi19.ui.country

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smaedev.covi19.DB.AppDatabase
import com.smaedev.covi19.DB.Country
import com.smaedev.covi19.MainActivity
import com.smaedev.covi19.Repository.CountryRepository
import kotlinx.coroutines.launch

class CountryViewModel : ViewModel() {

    private val countryRepository: CountryRepository
    val allCountries: LiveData<List<Country>>

    init {
        val countryDao = AppDatabase.getDatabase(MainActivity.applicationContext(), viewModelScope).countryDao()
        countryRepository = CountryRepository(countryDao)
        allCountries = countryRepository.allCountries
    }

    fun insert(country: String, death: Int, ill: Int, total: Int) = viewModelScope.launch {
        countryRepository.insertCountry(country,death, ill, total)
    }

    suspend fun createC(username: String, password: String) {
        //countryRepository.insertCountry(username, password)
    }
}