package com.smaedev.covi19.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.smaedev.covi19.ITanApi
import com.smaedev.covi19.adapter.CountryListAdapter
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.db.CountryDao
import com.smaedev.covi19.ui.country.CountryFragment
import com.smaedev.covi19.ui.country.recyclerViewC
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CountryRepository(private val countryDao: CountryDao) {

    suspend fun insert(country: Country) {
        countryDao.insert(country)
    }

    suspend fun insertAll(countries: List<Country>) {
        countryDao.insertAll(countries)
    }

    val allCountries: LiveData<List<Country>> = countryDao.getAlphabetizedCountries()


    //*****************Client de recuperation des pays

    private var client = OkHttpClient.Builder().addInterceptor {
        val request: Request = Request.Builder()
            .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php")
            .get()
            .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "2547575680msh1aee1093c9acb63p1f21b9jsn5ba8aa6b9d5e")
            .build()
        it.proceed(request)
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://coronavirus-monitor.p.rapidapi.com/coronavirus/")
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service = retrofit.create(ITanApi::class.java)
    private val countries = service.getCountries()

    fun fetchCountries(){

        countries.clone().enqueue(object: Callback<CountryFeed> {

            override fun onResponse(call: Call<CountryFeed>, response: Response<CountryFeed>) {

                val allCountries = response.body()

                allCountries.let {
                    println("Test------")

                    adapterC = CountryListAdapter(it!!, CountryFragment.getValue())
                    recyclerViewC.adapter = adapterC

                    /*for(country in it.countries_stat) {
                        println("ok${it.countries_stat}")
                        //Log.d("Contaminé","Pays ${country.country_name}")
                    }*/
                }
            }
            override fun onFailure(call: Call<CountryFeed>, t: Throwable) {
                Log.e("Pays", "Error : $t")
            }
        })
    }

}

class CountryFeed(val countries_stat : List<Country>)
lateinit var adapterC : CountryListAdapter