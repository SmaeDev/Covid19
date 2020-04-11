package com.smaedev.covid19.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.smaedev.covid19.R
import com.smaedev.covid19.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
                ViewModelProvider(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        binding = FragmentAboutBinding.bind(root)
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()

        binding.ibtBackAbout.setOnClickListener{findNavController().navigate(R.id.nav_home, null)}

        return root
    }
}
