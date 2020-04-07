package com.smaedev.covi19

import com.smaedev.covi19.db.Country
import com.smaedev.covi19.repository.CountryFeed
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiApp {

    @GET("cases_by_country.php")
    fun getCountries(): Call<CountryFeed>

    /*@POST("cases_by_country.php")
    fun sendCountry(@Body body: String?): Call<CountryFeed?>?*/
}