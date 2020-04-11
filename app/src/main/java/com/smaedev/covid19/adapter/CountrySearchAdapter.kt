package com.smaedev.covid19.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covid19.OnItemClickListener
import com.smaedev.covid19.R
import com.smaedev.covid19.databinding.RecyclerviewCountryBinding
import com.smaedev.covid19.db.Country

class CountrySearchAdapter(
    private var listCountries: List<Country>,
    private val listener: OnItemClickListener

) : RecyclerView.Adapter<CountrySearchViewHolder>() {

    private var filterListResult : List<Country> = listCountries

    //private var countries = emptyList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountrySearchViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recyclerview_country, parent, false)

        return CountrySearchViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CountrySearchViewHolder, position: Int) {
        val current = listCountries[position]

        //holder.binding.tvCountry.text = filterListResult[position].country_name
        holder.binding.country = current
        holder.binding.oneCountry.setOnClickListener{listener.onCountryClicked(current)}
    }

    internal fun setCountries(countries: List<Country>) {
        this.filterListResult = countries

        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listCountries.size
    }

     /*fun getFilter(): android.widget.Filter {
        return object : android.widget.Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch : String = constraint.toString()

                filterListResult = if (charSearch.isEmpty()){
                    listCountries
                } else{
                    val resultList = ArrayList<Country>()
                    for (row in listCountries){
                        if (row.country_name.toLowerCase().contains(charSearch.toLowerCase()))
                            resultList.add(row)
                    }
                    resultList
                }
                val filterResults = android.widget.Filter.FilterResults()
                filterResults.values = filterListResult

                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filterListResult = results!!.values as List<Country>
                notifyDataSetChanged()
            }

        }
    }*/
}

class CountrySearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val binding : RecyclerviewCountryBinding= RecyclerviewCountryBinding.bind(view)
}