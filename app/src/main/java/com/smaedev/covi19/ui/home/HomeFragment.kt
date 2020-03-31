package com.smaedev.covi19.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smaedev.covi19.adapter.CountryListAdapter
import com.smaedev.covi19.R
import com.smaedev.covi19.databinding.FragmentHomeBinding


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

        val tvTotalPays = binding.tvNbPays
        val nbPays : String = CountryListAdapter.totalpays.toString()
        tvTotalPays.text = nbPays

        /*val tvDeaths = binding.tvNbMorts)
        var nbDeath : String = CountryListAdapter.totalMort.toString()
        tvDeaths.setText(nbDeath)*/

        binding.btIPays.setOnClickListener {
            findNavController().navigate(R.id.nav_country, null)
        }
        return root
    }
}
