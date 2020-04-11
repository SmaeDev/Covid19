package com.smaedev.covid19.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AdviceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(advice: Advice)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(advices: List<Advice>)


    @Query("DELETE FROM advices")
    fun deleteAll()

    @Query("SELECT * from advices ORDER BY idAdvice ASC")
    fun getAlphabetizedAdvices(): LiveData<List<Advice>>
}