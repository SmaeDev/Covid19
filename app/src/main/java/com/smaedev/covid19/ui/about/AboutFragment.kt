package com.smaedev.covid19.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.smaedev.covid19.R
import com.smaedev.covid19.databinding.FragmentAboutBinding
import com.smaedev.covid19.ui.about.articles.AboutArticleFragment
import com.smaedev.covid19.ui.about.movies.AboutMovieFragment
import java.util.*

class AboutFragment : Fragment() {

    private lateinit var aboutViewModel: AboutViewModel
    private lateinit var binding: FragmentAboutBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private val tabIcons = intArrayOf(
        R.drawable.ic_library_books_black_24dp,
        R.drawable.ic_local_movies_black_24dp
    )

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        aboutViewModel =
                ViewModelProvider(this).get(AboutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about, container, false)
        binding = FragmentAboutBinding.bind(root)
        //(activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (activity as AppCompatActivity?)!!.supportActionBar!!.title = getString(R.string.TitleAboutFrag)

        viewPager = binding.aboutViewPager
        setupViewPager(viewPager)

        tabLayout = binding.aboutTabLayout
        tabLayout.setupWithViewPager(viewPager)
        setupTabIcons()

        //binding.ibtBackAbout.setOnClickListener{findNavController().navigateUp()}

        return root
    }

    private fun setupTabIcons() {
        tabLayout.getTabAt(0)?.setIcon(tabIcons.get(0))
        tabLayout.getTabAt(1)?.setIcon(tabIcons.get(1))
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(childFragmentManager)
        adapter.addFrag(AboutArticleFragment(), "Articles")
        adapter.addFrag(AboutMovieFragment(), "Vidéos")
        viewPager.adapter = adapter
    }

    internal class ViewPagerAdapter(manager: FragmentManager?) :
        FragmentPagerAdapter(manager!!) {
        private val mFragmentList: MutableList<Fragment> =  ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}
