package com.smaedev.covid19.db

import com.smaedev.covid19.repository.CountryFeed
import retrofit2.Call
import retrofit2.http.GET


interface ApiApp {
    @GET("cases_by_country.php")
    fun getCountries(): Call<CountryFeed>
}