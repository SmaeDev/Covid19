package com.smaedev.covid19.ui.about.articles

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smaedev.covid19.R
import com.smaedev.covid19.databinding.FragmentAboutArticleBinding
import com.smaedev.covid19.db.Country
import com.smaedev.covid19.db.AboutArticleParent

class AboutArticleFragment : Fragment() {

    var itemsData = ArrayList<Country>()
    lateinit var adapter: AboutArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_about_article, container, false)
        val binding: FragmentAboutArticleBinding = FragmentAboutArticleBinding.bind(root)

        val list = ArrayList<AboutArticleParent>(2)
        for (i in 0..2) {
            list.add(AboutArticleParent("Article $i"))

            when (i) {
                0 -> {
                    urlWebView = "https://www.indilmalek.com"
                }
                1 -> {
                    urlWebView =
                        "https://android.jlelse.eu/expandable-recycler-view-in-android-8d8927811ba7"
                }
                2 -> {
                    urlWebView =
                        "https://android.jlelse.eu/expandable-recycler-view-in-android-8d8927811ba7"
                }
            }
        }

        val adapter = AboutArticleAdapter(list)
        binding.articleRv.adapter = adapter

        adapter.setExpanded(false)

        return root
    }
}
lateinit var urlWebView: String