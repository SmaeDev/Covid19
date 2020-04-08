package com.smaedev.covi19.ui.country

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.smaedev.covi19.R
import com.smaedev.covi19.databinding.FragmentFragDetailCountryBinding
import com.smaedev.covi19.repository.dateMAJ

class FragDetailCountry : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_frag_detail_country, container, false)
        val binding : FragmentFragDetailCountryBinding = FragmentFragDetailCountryBinding.bind(root)

        //(activity as AppCompatActivity?)!!.supportActionBar!!.title = "Pays : $countrynameKey"
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        val countrynameKey = requireArguments().getString("COUNTRYNAME_KEY")

        binding.tvTitleDetail.text = "$countrynameKey "+getString(R.string.titleDetail)
        binding.tvSoutitle.text = getString(R.string.soutitleDetail1)+" $countrynameKey "+getString(R.string.soutitleDetail2)
        binding.Date.text = getString(R.string.date)+" $dateMAJ"

        binding.tvCases.text = requireArguments().getString("CASES_KEY")
        binding.tvTotalDeath.text = requireArguments().getString("DEATH_KEY")
        binding.tvRecovered.text = requireArguments().getString("TOTAL_REC_KEY")
        binding.tvNewDeath.text = requireArguments().getString("NEWDEATH_KEY")
        binding.tvNewCase.text = requireArguments().getString("NEWCASE_KEY")
        binding.tvCritical.text = requireArguments().getString("CRITICAL_KEY")
        binding.tvActive.text = requireArguments().getString("ACTIVECASE_KEY")
        binding.tvCasePer1m.text = requireArguments().getString("TOTALPER1M_KEY")

        return root
    }

}
