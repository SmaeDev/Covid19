package com.smaedev.covi19.Adapter

import android.app.SearchManager
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.smaedev.covi19.R
import com.smaedev.covi19.ui.country.CountryFragment


class FragDetailCountry : Fragment() {

    private  var country: TextView? =null
    private  var death: TextView? = null
    private  var cases: TextView? = null
    private  var total_recovered: TextView? = null
    private  var new_deaths: TextView? = null
    private  var new_cases: TextView? = null
    private  var serious_critical: TextView? = null
    private  var active_cases: TextView? = null
    private  var total_cases_per_1m_population: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_frag_detail_country, container, false)

        // Initialisation des variables ==============================
        country = root.findViewById(R.id.tvCountryname)
        death = root.findViewById(R.id.tvTotalDeath)
        cases = root.findViewById(R.id.tvCases)
        total_recovered = root.findViewById(R.id.tvRecovered)
        new_deaths = root.findViewById(R.id.tvNewDeath)
        new_cases = root.findViewById(R.id.tvNewCase)
        serious_critical = root.findViewById(R.id.tvCritical)
        active_cases = root.findViewById(R.id.tvActive)
        total_cases_per_1m_population = root.findViewById(R.id.tvCasePer1m)

        val countrynameKey = requireArguments().getString("COUNTRYNAME_KEY")

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Pays : "+countrynameKey

        val casesKey = requireArguments().getString("CASES_KEY")
        val deathKey = requireArguments().getString("DEATH_KEY")
        val totalRecKey = requireArguments().getString("TOTAL_REC_KEY")
        val newDeathKey = requireArguments().getString("NEWDEATH_KEY")
        val newCaseKey = requireArguments().getString("NEWCASE_KEY")
        val criticalKey = requireArguments().getString("CRITICAL_KEY")
        val activecasesKey = requireArguments().getString("ACTIVECASE_KEY")
        val totalper1mKey = requireArguments().getString("TOTALPER1M_KEY")

        //country?.setText("Les statistiques pour : "+countrynameKey)
        country?.setText("Les statistiques")
        cases?.setText(casesKey)
        death?.setText(deathKey)
        total_recovered?.setText(totalRecKey)
        new_deaths?.setText(newDeathKey)
        new_cases?.setText(newCaseKey)
        serious_critical?.setText(criticalKey)
        active_cases?.setText(activecasesKey)
        total_cases_per_1m_population?.setText(totalper1mKey)

        return root
    }

    companion object {

        private var instance: FragDetailCountry? = null

        fun applicationContext() : Context? {
            return instance?.context
        }
    }

    init {
        instance = this
    }

}
