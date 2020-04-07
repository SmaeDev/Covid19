package com.smaedev.covi19.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.OnItemClickListener
import com.smaedev.covi19.R
import com.smaedev.covi19.databinding.RecyclerviewCountrysearchBinding
import com.smaedev.covi19.db.Country
import java.util.ArrayList

class CountrySearchAdapter(
    private var listCountries: List<Country>,
    //private var countryName : String,
    private val listener: OnItemClickListener

) : RecyclerView.Adapter<CountrySearchViewHolder>(), Filterable {

    //internal var listCountries : List<Country> = ArrayList()

    internal lateinit var country: Country
    internal var filterListResult : List<Country> = listCountries
    private var searchCountryName : String? = null

    private var countries = emptyList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountrySearchViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.recyclerview_countrysearch, parent, false)
        //val binding: RecyclerviewCountryBinding = RecyclerviewCountryBinding.bind(itemView)

        return CountrySearchViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CountrySearchViewHolder, position: Int) {
        val current = listCountries[position]
        //val fragmentManager = (holder.itemView.context as FragmentActivity).supportFragmentManager

        //holder.countryName.text = filterListResult[position].country_name
        holder.binding.countrysearch = current
        //holder.countryCase.text =  String.format(current.cases+" cas")
        holder.binding.oneCountrySearch.setOnClickListener{listener.onCountryClicked(current)}
    }

    internal fun setCountries(countries: List<Country>) {
        this.filterListResult = countries

        notifyDataSetChanged()
    }

    internal fun setOneCountry(listCount: List<Country>): List<Country>{
        val pays = Country(country.country_name)
        this.listCountries = listOf(pays)
        notifyDataSetChanged()
        return listCountries
    }

    internal fun setManyCountry(name: String): List<Country> {
        val countryok = Country(name)

        notifyDataSetChanged()
        return listOf(countryok)
    }

    internal fun setOKCountry(country: Country) {
        this.country = country
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return listCountries.size
    }

    override fun getFilter(): android.widget.Filter {
        return object : android.widget.Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch : String = constraint.toString()

                if (charSearch.isEmpty()){
                    filterListResult = listCountries
                }
                else{
                    val resultList = ArrayList<Country>()
                    for (row in listCountries){
                        if (row.country_name.toLowerCase().contains(charSearch.toLowerCase()))
                            resultList.add(row)
                    }

                    filterListResult = resultList
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
    }
}

class CountrySearchViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val binding : RecyclerviewCountrysearchBinding= RecyclerviewCountrysearchBinding.bind(view)
    val countryName= binding.tvCountry
}