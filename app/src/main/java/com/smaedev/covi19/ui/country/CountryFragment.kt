package com.smaedev.covi19.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.smaedev.covi19.Adapter.CountryListAdapter
import com.smaedev.covi19.DB.Country
import com.smaedev.covi19.R
import kotlinx.android.synthetic.main.fragment_country.*
import okhttp3.*
import java.io.IOException


class CountryFragment : Fragment() {

    private lateinit var countryViewModel: CountryViewModel
    var toolbar: Toolbar? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fetchJson()
        countryViewModel =
                ViewModelProvider(this).get(CountryViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_country, container, false)
        val recyclerviewCountry = root.findViewById<RecyclerView>(R.id.recyclerviewCountry)
        recyclerviewCountry.layoutManager = LinearLayoutManager(context)


        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Pays contaminés"
        /*val adapter = CountryListAdapter()
        recyclerviewCountry.adapter = adapter
        recyclerviewCountry.layoutManager = LinearLayoutManager(context)

        countryViewModel.allCountries.observe(viewLifecycleOwner, Observer { countries ->
            // Update the cached copy of the words in the adapter.
            countries.let { adapter.setCountries(it) }
        })*/
        return root
    }

    fun fetchJson(){
        println("Test")
        val request= Request.Builder()
            .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php")
            .get()
            .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "2547575680msh1aee1093c9acb63p1f21b9jsn5ba8aa6b9d5e")
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println("ok"+body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, CountryFeed::class.java)

                runOnUiThread() {
                    recyclerviewCountry.adapter = CountryListAdapter(homeFeed)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

        })
    }

    fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        activity?.runOnUiThread(action)
    }
}
class CountryFeed(val countries_stat : List<Country>)