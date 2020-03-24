package com.smaedev.covi19.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.R
import com.smaedev.covi19.ui.country.CountryFeed
import com.smaedev.covi19.ui.country.CountryViewModel

class CountryListAdapter(val countryFeed: CountryFeed) : RecyclerView.Adapter<CountryViewHolder>() {

    private var countryViewModel: CountryViewModel? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recyclerview_country, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {
        val current = countryFeed.countries_stat.get(position)
        //val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager


        for ( i in 1..totalpays!!){
            /*val totalDeath: String = countryFeed.countries_stat[i].deaths
            total += totalDeath.toInt()*/

            countryViewModel?.insertCountry(current)
        }

        //val context = holder.itemView.context
        holder.countryName.text = current.country_name
        holder.countryCase.text = current.cases+" cas"

        val countryName: String = current.country_name
        val cases: String = current.cases
        val deaths: String = current.deaths
        val total_recovered: String = current.total_recovered
        val new_deaths: String = current.new_deaths
        val new_cases: String = current.new_cases
        val serious_critical: String = current.serious_critical
        val active_cases: String? = current.active_cases
        val total_cases_per_1m_population: String = current.total_cases_per_1m_population

        holder.countryName.setOnClickListener {view ->
            //Toast.makeText(context, "Pays "+ current.country_name, Toast.LENGTH_LONG).show()
            /*openCountryDetailFragment(current.country_name, current.cases, current.deaths,current.total_recovered,
                current.new_deaths,current.new_cases,current.serious_critical,current.active_cases,current.total_cases_per_1m_population)
            */

            val bundle = bundleOf(
                "COUNTRYNAME_KEY" to countryName,
                "CASES_KEY" to cases,
                "DEATH_KEY" to deaths,
                "TOTAL_REC_KEY" to total_recovered,
                "NEWDEATH_KEY" to new_deaths,
                "NEWCASE_KEY" to new_cases,
                "CRITICAL_KEY" to serious_critical,
                "ACTIVECASE_KEY" to active_cases,
                "TOTALPER1M_KEY" to total_cases_per_1m_population
            )
            view.findNavController().navigate(R.id.fragDetailCountry, bundle)
        }

    }

    /*internal fun setCountries(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }*/

    override fun getItemCount(): Int {
        return countryFeed.countries_stat.count()
    }

    /*private fun openCountryDetailFragment(
        countryName: String,
        cases: String,
        deaths: String,
        total_recovered: String,
        new_deaths: String,
        new_cases: String,
        serious_critical: String,
        active_cases: String,
        total_cases_per_1m_population: String
    ) {

        val data = Bundle()
        data.putString("COUNTRYNAME_KEY", countryName)
        data.putString("CASES_KEY", cases)
        data.putString("DEATH_KEY", deaths)
        data.putString("TOTAL_REC_KEY", total_recovered)
        data.putString("NEWDEATH_KEY", new_deaths)
        data.putString("NEWCASE_KEY", new_cases)
        data.putString("CRITICAL_KEY", serious_critical)
        data.putString("ACTIVECASE_KEY", active_cases)
        data.putString("TOTALPER1M_KEY", total_cases_per_1m_population)

        //fragment.setArguments(data)
    }*/

    companion object {
        var totalpays: Int? = null
        var totalCas: Int? = null
        var totalMort: Int? = null
        var total: Int = 0
    }
    init {
        totalpays = countryFeed.countries_stat.count()
        totalMort = total

    }
}

class CountryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val countryName: TextView = itemView.findViewById(R.id.tvCountry)
    val countryCase: TextView = itemView.findViewById(R.id.tvCas)
}
