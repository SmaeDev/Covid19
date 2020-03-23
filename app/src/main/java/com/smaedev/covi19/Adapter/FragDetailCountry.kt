package com.smaedev.covi19.Adapter

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentManager

import com.smaedev.covi19.R

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

        val countrynameKey = arguments!!.getString("COUNTRYNAME_KEY")
        val casesKey = arguments!!.getString("CASES_KEY")
        val deathKey = arguments!!.getString("DEATH_KEY")
        val totalRecKey = arguments!!.getString("TOTAL_REC_KEY")
        val newDeathKey = arguments!!.getString("NEWDEATH_KEY")
        val newCaseKey = arguments!!.getString("NEWCASE_KEY")
        val criticalKey = arguments!!.getString("CRITICAL_KEY")
        val activecasesKey = arguments!!.getString("ACTIVECASE_KEY")
        val totalper1mKey = arguments!!.getString("TOTALPER1M_KEY")

        country?.setText(countrynameKey)
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

        var fragment: FragmentManager? = null

        private var instance: FragDetailCountry? = null

        fun applicationContext() : Context? {
            return instance?.context
        }
    }

    init {
        instance = this
        fragment = fragmentManager
    }
}
