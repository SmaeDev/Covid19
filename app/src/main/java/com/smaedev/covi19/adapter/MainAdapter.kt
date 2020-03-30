package com.smaedev.covi19.adapter
/*
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.R
import com.smaedev.covi19.repository.CountryFeed
import kotlinx.android.synthetic.main.recyclerview_country.view.*

class MainAdapter(val countryFeed: CountryFeed) : RecyclerView.Adapter<CustomViewHolder>() {

    val videosTitle = listOf("ok","rgr","rtgr","ftgr")

    override fun getItemCount():Int{
        return countryFeed.countries_stat.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellforRoww = layoutInflater.inflate(R.layout.recyclerview_country, parent, false)
        return CustomViewHolder(cellforRoww)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val country = countryFeed.countries_stat.get(position)
        //holder.userItemView.text = current.countryName
        holder.view.tvCountry?.text = country.country_name
    }

}
class CustomViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val userItemView: TextView = itemView.findViewById(R.id.tvCountry)
}*/