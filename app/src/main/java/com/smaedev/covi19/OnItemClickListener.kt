package com.smaedev.covi19

import com.smaedev.covi19.db.Country

interface OnItemClickListener {

    fun onCountryClicked(country: Country)
}