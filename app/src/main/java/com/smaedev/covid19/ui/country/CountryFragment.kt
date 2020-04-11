package com.smaedev.covid19.ui.country

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covid19.OnItemClickListener
import com.smaedev.covid19.db.Country
import com.smaedev.covid19.R
import com.smaedev.covid19.adapter.CountryListAdapter
import com.smaedev.covid19.databinding.FragmentCountryBinding
import com.smaedev.covid19.repository.totalCountries

class CountryFragment : Fragment(), OnItemClickListener {

    private lateinit var countryViewModel: CountryViewModel
    lateinit var adapterC : CountryListAdapter
    lateinit var recyclerViewC: RecyclerView

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
        val root = inflater.inflate(R.layout.fragment_country, container, false)
        val binding : FragmentCountryBinding = FragmentCountryBinding.bind(root)

        countryViewModel =ViewModelProvider(this).get(CountryViewModel::class.java)
        countryViewModel.getCountries()

        recyclerViewC = binding.recyclerviewCountry
        recyclerViewC.layoutManager = LinearLayoutManager(context)

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.TitleCountryFrag)

        countryViewModel.allCountries.observe(viewLifecycleOwner, Observer { countries ->
            countries?.let {
                adapterC = CountryListAdapter(it, getValue())
                recyclerViewC.adapter = adapterC
                adapterC.setCountries(it)

                totalCountries = adapterC.itemCount
               // Log.d("Contaminé ///////","Pays ${it}")
            }
        })

        return root
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
       findNavController().navigate(R.id.fragDetailCountry, bundle)
    }
}
