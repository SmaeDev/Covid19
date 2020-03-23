package com.smaedev.covi19.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.smaedev.covi19.Adapter.CountryListAdapter
import com.smaedev.covi19.R
import com.smaedev.covi19.ui.country.CountryFragment


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var recyclerViewPays: RecyclerView? = null
    private var btIPays: Button? = null
    private var btIConseils:Button? = null
    private var btIVirus:Button? = null

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val tvTotalPays = root.findViewById<TextView>(R.id.tvNbPays)
        var nbPays : String = CountryListAdapter.totalpays.toString()
        tvTotalPays.setText(nbPays)


        /*val tvDeaths: TextView = root.findViewById(R.id.tvNbMorts)
        var nbDeath : String = CountryListAdapter.totalMort.toString()
        tvDeaths.setText(nbDeath)*/


        val btIPays = root.findViewById<ImageButton>(R.id.btIPays)
        btIPays.setOnClickListener {
            val countryFragment = CountryFragment()
            val fragmentManager: FragmentManager = activity!!.supportFragmentManager
            val fragmenttransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmenttransaction.replace(R.id.nav_host_fragment, countryFragment)
                .addToBackStack(null)
                .commit()
        }

        return root
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view!!, savedInstanceState)

        //Log.d(TAG, "initRecyclerView: init recyclerview");
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerViewPays?.setLayoutManager(layoutManager)
        recyclerViewPays?.setHasFixedSize(true)
        //loadcountries()
    }*/

}
