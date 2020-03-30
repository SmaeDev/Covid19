package com.smaedev.covi19.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.R

/*class CountryListAdapterOffline internal constructor(val context: Context?) : RecyclerView.Adapter<CountryListAdapterOffline.CountryViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var countries = emptyList<Country>()
    var country : String? = null

    inner class CountryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val countryName: TextView = itemView.findViewById(R.id.tvCountry)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_country, parent, false)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val current = countries[position]
        holder.countryName.text = current.country_name
        holder.countryName.setOnClickListener {
            country = current.country_name
            //openWordDetailFragment(current.word)
        }
    }

    internal fun setCountries(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    override fun getItemCount() = countries.size

}*/