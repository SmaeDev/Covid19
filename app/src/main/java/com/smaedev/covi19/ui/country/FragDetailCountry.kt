package com.smaedev.covi19.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smaedev.covi19.R
import com.smaedev.covi19.databinding.FragmentFragDetailCountryBinding
import com.smaedev.covi19.repository.dateMAJ


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
        val binding : FragmentFragDetailCountryBinding = FragmentFragDetailCountryBinding.bind(root)

        // Initialisation des variables ==============================
        val titleDetail = binding.tvTitleDetail
        val sousTitleDetail = binding.tvSoutitle
        val date = binding.Date
        val death = binding.tvTotalDeath
        val cases = binding.tvCases
        val totalRecovered = binding.tvRecovered
        val newDeaths = binding.tvNewDeath
        val newCases = binding.tvNewCase
        val seriousCritical = binding.tvCritical
        val activeCases = binding.tvActive
        val totalCasesPer1mPopulation = binding.tvCasePer1m

        val countrynameKey = requireArguments().getString("COUNTRYNAME_KEY")

        //(activity as AppCompatActivity?)!!.supportActionBar!!.title = "Pays : $countrynameKey"
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        titleDetail.text = "$countrynameKey "+getString(R.string.titleDetail)
        sousTitleDetail.text = getString(R.string.soutitleDetail1)+" $countrynameKey "+getString(R.string.soutitleDetail2)
        date.text = getString(R.string.date)+" $dateMAJ"

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
