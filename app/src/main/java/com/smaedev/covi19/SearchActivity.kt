package com.smaedev.covi19

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.adapter.CountrySearchAdapter
import com.smaedev.covi19.databinding.ActivitySearchBinding
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.repository.CountryRepository
import com.smaedev.covi19.ui.country.CountryFragment
import com.smaedev.covi19.ui.country.CountryViewModel
import kotlinx.android.synthetic.main.activity_search.*
import java.util.*
import kotlin.collections.ArrayList

class SearchActivity : Activity(), OnItemClickListener{

    private lateinit var countryViewModel: CountryViewModel
    private lateinit var searchText: String
    lateinit var binding: ActivitySearchBinding
    private lateinit var recyclerViewSearch: RecyclerView
    private lateinit var adapterSearch: CountrySearchAdapter

    var dataList : MutableList<Country> = ArrayList()
    val displayList = java.util.ArrayList<Country>()

    companion object {
        private var instance: SearchActivity? = null

        fun getValue(): OnItemClickListener {
            return this.instance!!
        }
    }
    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_search)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        //countryViewModel =ViewModelProvider(CountryFragment.).get(CountryViewModel::class.java)
        //countryViewModel.getCountries

        recyclerViewSearch = binding.recyclerviewSearchCountry
        recyclerViewSearch.layoutManager = LinearLayoutManager(this)

        //adapterSearch = CountrySearchAdapter(listPaysSearch, getValue())
       // recyclerViewSearch.adapter = adapterSearch

        handleIntent(intent)
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)

        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)

            if (query!!.isNotEmpty()) {
                displayList.clear()

                val search = query.toLowerCase(Locale.getDefault())
                listPaysSearch.forEach {
                    if (it.country_name.toLowerCase(Locale.getDefault()).contains(search)) {
                        displayList.add(it)
                    }
                }
                recyclerViewSearch.adapter?.notifyDataSetChanged()

            } else {
                displayList.clear()
                displayList.addAll(listPaysSearch)
                recyclerViewSearch.adapter?.notifyDataSetChanged()
            }

            adapterSearch.filter.filter(query)

            //doMySearch(query)

            /*(this.applicationContext as ApplicationApp).setSearchText(searchText)
            finish()*/
            //val c = countryRepository.listPays.(query, null)

            /*intent.getStringExtra(SearchManager.QUERY)?.also { query ->
                doMySearch(query)
            }*/

            //Toast.makeText(this, "haha$query", Toast.LENGTH_SHORT).show()
            //use the query to search your data somehow
        }
    }

    /*override fun onResume() {
        super.onResume()
        searchText = (this.applicationContext as ApplicationApp).getSearchText().toString()
        //refresh your list here...
    }*/

    override fun onSearchRequested(): Boolean {
        val appData = Bundle().apply {
            //putBoolean(JARGON, true)
        }
        startSearch(null, false, appData, false)

        return true
    }

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
        findNavController(R.id.nav_searchActivity).navigate(R.id.fragDetailCountry, bundle)
    }

}
