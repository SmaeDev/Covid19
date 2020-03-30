package com.smaedev.covi19.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smaedev.covi19.R


class FragDetailCountry : Fragment() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_frag_detail_country, container, false)

        // Initialisation des variables ==============================
        val country : TextView = root.findViewById(R.id.tvCountryname)
        val death : TextView = root.findViewById(R.id.tvTotalDeath)
        val cases : TextView = root.findViewById(R.id.tvCases)
        val totalRecovered : TextView = root.findViewById(R.id.tvRecovered)
        val newDeaths : TextView = root.findViewById(R.id.tvNewDeath)
        val newCases : TextView = root.findViewById(R.id.tvNewCase)
        val seriousCritical : TextView = root.findViewById(R.id.tvCritical)
        val activeCases : TextView = root.findViewById(R.id.tvActive)
        val totalCasesPer1mPopulation : TextView = root.findViewById(R.id.tvCasePer1m)

        val countrynameKey = requireArguments().getString("COUNTRYNAME_KEY")

        (activity as AppCompatActivity?)!!.supportActionBar!!.title = "Pays : $countrynameKey"

        //country?.setText("Les statistiques pour : "+countrynameKey)
        country.text = getString(R.string.enteteDetailPays)
        cases.text = requireArguments().getString("CASES_KEY")
        death.text = requireArguments().getString("DEATH_KEY")
        totalRecovered.text = requireArguments().getString("TOTAL_REC_KEY")
        newDeaths.text = requireArguments().getString("NEWDEATH_KEY")
        newCases.text = requireArguments().getString("NEWCASE_KEY")
        seriousCritical.text = requireArguments().getString("CRITICAL_KEY")
        activeCases.text = requireArguments().getString("ACTIVECASE_KEY")
        totalCasesPer1mPopulation.text = requireArguments().getString("TOTALPER1M_KEY")

        return root
    }

}
