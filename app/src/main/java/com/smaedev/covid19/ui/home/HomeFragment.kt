package com.smaedev.covid19.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smaedev.covid19.R
import com.smaedev.covid19.databinding.FragmentHomeBinding
import com.smaedev.covid19.repository.totalCases
import com.smaedev.covid19.repository.totalCountries
import com.smaedev.covid19.repository.totalDeath


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.bind(root)

        binding.tvNbPays.text = totalCountries.toString()
        binding.tvNbCas.text = totalCases.toString()
        binding.tvNbMorts.text = totalDeath.toString()
        //val nbPays : String = CountryListAdapter.totalpays.toString()
        //tvTotalPays.text = nbPays

        /*val tvDeaths = binding.tvNbMorts)
        var nbDeath : String = CountryListAdapter.totalMort.toString()
        tvDeaths.setText(nbDeath)*/

        binding.btICountry.setOnClickListener {findNavController().navigate(R.id.nav_country, null)}
        binding.btIAdvice.setOnClickListener {findNavController().navigate(R.id.nav_advice, null)}
        binding.btIVirus.setOnClickListener {findNavController().navigate(R.id.nav_about, null)}

        binding.constraintLayoutCountry.setOnClickListener {findNavController().navigate(R.id.nav_country, null)}
        binding.constraintLayoutAdvice.setOnClickListener {findNavController().navigate(R.id.nav_advice, null)}
        binding.constraintLayoutVirus.setOnClickListener {findNavController().navigate(R.id.nav_about, null)}

        return root
    }
}
