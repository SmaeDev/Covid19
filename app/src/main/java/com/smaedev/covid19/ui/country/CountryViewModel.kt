package com.smaedev.covid19.ui.country

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.smaedev.covid19.AppDatabase
import com.smaedev.covid19.db.Country
import com.smaedev.covid19.repository.CountryRepository
import com.smaedev.covid19.repository.totalCases

class CountryViewModel : ViewModel() {

    private val TAG : String? = CountryViewModel::class.simpleName

    private val countryDao = AppDatabase.database.countryDao()

    //val countryDao = AppDatabase.getDatabase(MainActivity.mainContext(), viewModelScope).countryDao()
    private val countryRepository: CountryRepository = CountryRepository(countryDao)

    val allCountries: LiveData<List<Country>> = countryRepository.allCountries

    fun getCountryByName(countryName: String?): LiveData<List<Country>> = countryRepository.getCountry(countryName)

    fun getCountries() = countryRepository.fetchCountries()

    /*fun getTotalStatistics() : LiveData<Double> {

        return Transformations.map(allCountries, it -> {
            val totalCountry : Double? = null

    }*/

    /*fun insertCountry(country : Country) = viewModelScope.launch {
        countryRepository.insert(country)
    }

    fun insertListCountry(countries : List<Country>) = viewModelScope.launch {
        countryRepository.insertAll(countries)
    }*/

    override fun onCleared() {
        super.onCleared()
        Log.d("ok ---------", "onCleared() appel")
    }

}