package com.smaedev.covi19.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smaedev.covi19.Repository.CountryRepository

class CountryViewModel : ViewModel() {

    private var countryRepository: CountryRepository? = null

    fun checkCountryExist(countryname: String): Boolean? {
        return countryRepository?.countryExist(countryname)
    }

    class Factory(ctxt: Context) : ViewModelProvider.Factory {

        private val ctxt: Context

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CountryViewModel() as T
        }

        init {
            this.ctxt = ctxt.applicationContext
        }
    }
}