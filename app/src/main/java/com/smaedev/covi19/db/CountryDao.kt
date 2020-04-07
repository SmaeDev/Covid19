package com.smaedev.covi19.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(country: Country)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<Country>)

    @Query("SELECT * from countries ORDER BY country_name ASC")
    fun getAlphabetizedCountries(): LiveData<List<Country>>

    @Query("DELETE FROM countries")
    suspend fun deleteAll()

    @Query("SELECT * FROM countries  WHERE country_name LIKE :query")
    fun getCountryByName(query: String?): LiveData<List<Country>>


    /* @Query("SELECT * FROM countries WHERE country_name LIKE :country")
     fun getACountry(country: String?): Country?

     @Query("SELECT * FROM countries WHERE country_name LIKE :country")
     fun getOKCountry(country: String?): LiveData<Country> */
}