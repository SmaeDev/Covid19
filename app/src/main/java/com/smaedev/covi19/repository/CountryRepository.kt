package com.smaedev.covi19.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.smaedev.covi19.db.countries
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.db.CountryDao
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CountryRepository(private val countryDao: CountryDao) {

    val allCountries: LiveData<List<Country>> = countryDao.getAlphabetizedCountries()

    fun getCountry(countryName: String?): LiveData<List<Country>> = countryDao.getCountryByName(countryName)

     fun fetchCountries(){
        countries.clone().enqueue(object: Callback<CountryFeed> {
            override fun onResponse(call: Call<CountryFeed>, response: Response<CountryFeed>) {

                val allCountries = response.body()
                dateMAJ = response.body()?.statistic_taken_at.toString()

                allCountries.let {
                    countryDao.insertAll(it!!.countries_stat)
                    for(country in it.countries_stat) {
                        dataList.add(country.country_name)
                    }
                }
            }
            override fun onFailure(call: Call<CountryFeed>, t: Throwable) {
                Log.e("Pays search", "Error : $t")
            }
        })
    }

    /*suspend fun insert(country: Country) {
       countryDao.insert(country)
   }

    fun insertAll(countries: List<Country>) {
        countryDao.insertAll(countries)
    }*/
}

class CountryFeed(val countries_stat : List<Country>, val statistic_taken_at: String)
var dataList = ArrayList<String>()
var dateMAJ: String? = null


var totalCountries: Int? = null
var totalCases: Int? = null
var totalDeath: Int? = null