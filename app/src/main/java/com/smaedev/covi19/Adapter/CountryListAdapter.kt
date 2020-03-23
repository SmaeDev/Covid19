package com.smaedev.covi19.Adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.R
import com.smaedev.covi19.ui.country.CountryFeed

class CountryListAdapter(val countryFeed: CountryFeed) : RecyclerView.Adapter<CountryViewHolder>() {
    //private var countries = emptyList<Country>()

    val fragment = FragDetailCountry()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recyclerview_country, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {

        /*for ( i in 1..position){
            val totalDeath: String = countryFeed.countries_stat[i].deaths
            total += totalDeath.toInt()
        }*/

        val current = countryFeed.countries_stat.get(position)
        val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager

        val context = holder.itemView.context
        holder.countryName.text = current.country_name
        holder.countryCase.text = current.cases+" cas"

        holder.countryName.setOnClickListener {
            //Toast.makeText(context, "Pays "+ current, Toast.LENGTH_LONG).show()
            openCountryDetailFragment(current.country_name, current.cases, current.deaths,current.total_recovered,
                current.new_deaths,current.new_cases,current.serious_critical,current.active_cases,current.total_cases_per_1m_population)

            val fragmenttransaction: FragmentTransaction? = fragmentManager.beginTransaction()
        fragmenttransaction!!.replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()
        }

    }

    /*internal fun setCountries(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }*/

    override fun getItemCount(): Int {
        return countryFeed.countries_stat.count()
    }

    private fun openCountryDetailFragment(
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

        fragment.setArguments(data)

        /*MainActivity.fragment?.beginTransaction()!!.replace(R.id.nav_host_fragment, fragment)
            .addToBackStack(null)
            .commit()*/
    }

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
