package com.smaedev.covi19.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(country: Country)

    @Query("SELECT * FROM countries WHERE countries.country_name LIKE :country")
    fun getCountries(country: String?): Country?

    @Query("SELECT * from countries ORDER BY country_name ASC")
    fun getAlphabetizedCountries(): LiveData<List<Country>>

    @Query("DELETE FROM countries")
    suspend fun deleteAll()
}