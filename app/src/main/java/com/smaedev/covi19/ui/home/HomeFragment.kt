package com.smaedev.covi19.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smaedev.covi19.adapter.CountryListAdapter
import com.smaedev.covi19.R


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val tvTotalPays = root.findViewById<TextView>(R.id.tvNbPays)
        val nbPays : String = CountryListAdapter.totalpays.toString()
        tvTotalPays.text = nbPays

        /*val tvDeaths: TextView = root.findViewById(R.id.tvNbMorts)
        var nbDeath : String = CountryListAdapter.totalMort.toString()
        tvDeaths.setText(nbDeath)*/

        val btIPays = root.findViewById<ImageButton>(R.id.btIPays)
        btIPays.setOnClickListener {
            findNavController().navigate(R.id.nav_country, null)
        }
        return root
    }
}
