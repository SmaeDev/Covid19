package com.smaedev.covid19.db

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
private val service = retrofit.create(ApiApp::class.java)
val countries = service.getCountries()
