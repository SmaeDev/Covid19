package com.smaedev.covi19.ui.country

import android.os.Bundle
import android.view.*
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.R
import com.smaedev.covi19.databinding.FragmentCountryBinding

class CountryFragment : Fragment(), OnItemClickListener {

    private lateinit var countryViewModel: CountryViewModel

    var toolbar: Toolbar? = null

    companion object {
        private var instance: CountryFragment? = null

        fun getValue(): OnItemClickListener {
            return this.instance!!
        }
    }
    init {
        instance = this
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        countryViewModel =ViewModelProvider(this).get(CountryViewModel::class.java)
        countryViewModel.getCountries()

        val root = inflater.inflate(R.layout.fragment_country, container, false)
        val binding : FragmentCountryBinding = FragmentCountryBinding.bind(root)


        recyclerViewC = binding.recyclerviewCountry
        recyclerViewC.layoutManager = LinearLayoutManager(context)


        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.TitleCountryFrag)

        /*countryViewModel.allCountries.observe(viewLifecycleOwner, Observer { countries ->
            // Update the cached copy of the words in the adapter.
            countries.let { adapterC.setCountries(it) }
        })*/

        return root
    }

    /*override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.main, menu)
        val searchItem = menu.findItem(R.id.action_search)

        if (searchItem != null){
            val searchView = searchItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

                override fun onQueryTextSubmit(query : String?): Boolean{
                    searchView.clearFocus()
                    searchView.setQuery("", false)
                    searchItem.collapseActionView()
                    //Toast.makeText(this@MainActivity, "Recherche $query", Toast.LENGTH_LONG).show()
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return true
                }
            })
        }

        return super.onCreateOptionsMenu(menu, inflater)
    }*/

    override fun onCountryClicked(country: Country) {
        val bundle = bundleOf(
            "COUNTRYNAME_KEY" to country.country_name,
            "CASES_KEY" to country.cases,
            "DEATH_KEY" to country.deaths,
            "TOTAL_REC_KEY" to country.total_recovered,
            "NEWDEATH_KEY" to country.new_deaths,
            "NEWCASE_KEY" to country.new_cases,
            "CRITICAL_KEY" to country.serious_critical,
            "ACTIVECASE_KEY" to country.active_cases,
            "TOTALPER1M_KEY" to country.total_cases_per_1m_population
        )
       findNavController().navigate(R.id.fragDetailCountry, bundle)
    }
}


lateinit var recyclerViewC: RecyclerView