package com.smaedev.covid19.advice

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.smaedev.covid19.AppDatabase
import com.smaedev.covid19.db.Advice
import com.smaedev.covid19.repository.AdviceRepository


class AdviceViewModel : ViewModel() {

    private val adviceDao = AppDatabase.database.adviceDao()

    //val adviceDao = AppDatabase.getDatabase(MainActivity.mainContext(), viewModelScope).adviceDao()
    private val adviceRepository: AdviceRepository = AdviceRepository(adviceDao)
    val allAdvices: LiveData<List<Advice>> = adviceRepository.allAdvices

    fun getAdvices() = adviceRepository.fetchAdvices()
}