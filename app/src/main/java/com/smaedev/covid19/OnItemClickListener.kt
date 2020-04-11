package com.smaedev.covid19

import com.smaedev.covid19.db.Country

interface OnItemClickListener {

    fun onCountryClicked(country: Country)
}