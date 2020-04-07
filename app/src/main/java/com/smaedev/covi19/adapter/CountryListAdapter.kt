package com.smaedev.covi19.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.OnItemClickListener
import com.smaedev.covi19.R
import com.smaedev.covi19.databinding.RecyclerviewCountryBinding
import com.smaedev.covi19.db.Country

class CountryListAdapter(
    private val listCountries: List<Country>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CountryViewHolder>() {

    private var countries = emptyList<Country>()
    private var searchCountryName : String? = null

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
        val current = listCountries[position]
        //val current = listCountries.value?.get(position)
        //val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager

        holder.binding.country = current
        //holder.countryCase.text =  String.format(current.cases+" cas")
        holder.binding.oneCountry.setOnClickListener{listener.onCountryClicked(current)}
    }

    internal fun setCountries(countries: List<Country>) {
        this.countries = countries
        notifyDataSetChanged()
    }

    internal fun setOneCountry(countryname : String): Country? {
        this.searchCountryName = countryname
        return Country(countryname)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listCountries.count()
        //return listCountries.value!!.size
    }
}

class CountryViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val binding : RecyclerviewCountryBinding = RecyclerviewCountryBinding.bind(view)
    //val countryCase= binding.tvCas
}
