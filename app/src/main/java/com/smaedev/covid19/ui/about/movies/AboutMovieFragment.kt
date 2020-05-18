package com.smaedev.covid19.ui.about.movies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.smaedev.covid19.R
import com.smaedev.covid19.databinding.FragmentAboutMovieBinding
import com.smaedev.covid19.db.AboutMovieParent

class AboutMovieFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about_movie, container, false)
        val binding : FragmentAboutMovieBinding= FragmentAboutMovieBinding.bind(root)

        val list = ArrayList<AboutMovieParent>(2)
        for (i in 0..2)
            list.add(AboutMovieParent("Vidéo $i"))

        val adapter = AboutMovieAdapter(list)
        binding.movieRv.adapter = adapter

        adapter.setExpanded(false)

        return root
    }

}
