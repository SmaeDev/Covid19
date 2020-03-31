package com.smaedev.covi19.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.R
import com.smaedev.covi19.databinding.RecyclerviewCountryBinding
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.repository.CountryFeed
import com.smaedev.covi19.ui.country.OnItemClickListener

class CountryListAdapter(
    private val countryFeed: CountryFeed,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CountryViewHolder>() {

    private var countries = emptyList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recyclerview_country, parent, false)
        //val binding: RecyclerviewCountryBinding = RecyclerviewCountryBinding.bind(itemView)

        return CountryViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {
        val current = countryFeed.countries_stat[position]
        //val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager

        /*for ( i in 1..totalpays!!){
            val totalDeath: String = countryFeed.countries_stat[i].deaths
            total += totalDeath.toInt()
        }*/

        //val context = holder.itemView.context
        holder.countryName.text = current.country_name
        holder.countryCase.text =  String.format(current.cases+" cas")
        holder.countryName.setOnClickListener{listener.onCountryClicked(current)}
    }

    internal fun setCountries(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return countryFeed.countries_stat.count()
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


    private val binding : RecyclerviewCountryBinding = RecyclerviewCountryBinding.bind(view)

    val countryName= binding.tvCountry
    val countryCase= binding.tvCas
}
