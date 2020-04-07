package com.smaedev.covi19.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.smaedev.covi19.MainActivity
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.db.CountryDao

class CountryRepository(private val countryDao: CountryDao) {

    var query : String = MainActivity.selected.toString()

    var countryname : String? = null
    val allCountries: LiveData<List<Country>> = countryDao.getAlphabetizedCountries()

    val oneCountry: LiveData<List<Country>> = countryDao.getCountryByName(query)

    fun getCountry(countryName: String?): LiveData<List<Country>> = countryDao.getCountryByName(countryName)


    /*var country: Country? = null
    var countryList: List<Country>? = null

    init {
        country = null
        countryList = null
    }


    fun getOneCountry(countryName: String?): Country? {
        //countryDao.getCountryByName(countryName)
        return countryDao.getACountry(countryName)
    }*/



    //lateinit var listPays : List<Country>

    suspend fun insert(country: Country) {
        countryDao.insert(country)
    }

    fun insertAll(countries: List<Country>) {
        countryDao.insertAll(countries)
    }

    //*****************Client de recuperation des pays**************//
   /* fun fetchCountries(){
        countries.clone().enqueue(object: Callback<CountryFeed> {
            override fun onResponse(call: Call<CountryFeed>, response: Response<CountryFeed>) {

                val allCountries = response.body()
                dateMAJ = response.body()?.statistic_taken_at.toString()
                //println("Test------:$dateMAJ")

                allCountries.let {

                    listPays = ArrayList()
                    listPays = it!!.countries_stat

                    //countryDao.insertAll(it.countries_stat)

                    for(country in it.countries_stat) {

                        //println("ok liste ${country.country_name}")
                        //Log.d("Contaminé","Pays ${country.country_name}")
                    }
                }
            }
            override fun onFailure(call: Call<CountryFeed>, t: Throwable) {
                Log.e("Pays", "Error : $t")
            }
        })
    }*/

}

class CountryFeed(val countries_stat : List<Country>, val statistic_taken_at: String)
lateinit var dateMAJ: String
