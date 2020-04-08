package com.smaedev.covi19

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.view.Menu
import android.widget.CursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.smaedev.covi19.adapter.CountryListAdapter
import com.smaedev.covi19.databinding.ActivityMainBinding
import com.smaedev.covi19.repository.CountryFeed
import com.smaedev.covi19.repository.dataList
import com.smaedev.covi19.ui.country.CountryViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var strArrData = arrayOf("No Suggestions")

    private lateinit var countryViewModel: CountryViewModel

    private lateinit var cursorAdapter: SimpleCursorAdapter
    private lateinit var searchView: SearchView
    lateinit var binding: ActivityMainBinding


    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object {
        private var INSTANCE: MainActivity? = null

        var selected : String? = null
    }

    init {
        INSTANCE = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        //setContentView(binding.root)

        setSupportActionBar(binding.tool.toolbar)
        supportActionBar!!.show()

        /*for ( i in 1..totalCountry!!){
           val totalDeath: String = countryFeed.countries_stat[i].deaths
           total += totalDeath.toInt()
       }*/

        /*val fab = binding.fab
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
        val drawerLayout = binding.drawerLayout
        val navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_country, R.id.nav_about
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        
        val from = arrayOf("country_name")
        val to = intArrayOf(android.R.id.text1)

        // setup SimpleCursorAdapter
        cursorAdapter = SimpleCursorAdapter(
            this@MainActivity, android.R.layout.simple_spinner_dropdown_item, null, from, to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )

        countryViewModel = ViewModelProvider(this).get(CountryViewModel::class.java)
        countryViewModel.getCountries()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)

        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        /*searchView.setSearchableInfo(
            searchManager.getSearchableInfo(
                ComponentName(
                    this,
                    SearchActivity::class.java
                )
            )
        )*/
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.isSubmitButtonEnabled = true
        searchView.isIconified = false
        searchView.suggestionsAdapter = cursorAdapter

        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionClick(position: Int): Boolean {
                // Add clicked text to search box
                val ca: androidx.cursoradapter.widget.CursorAdapter? = searchView.suggestionsAdapter
                val cursor = ca?.cursor
                cursor?.moveToPosition(position)
                selected = cursor?.getString(cursor.getColumnIndex("country_name"))

                searchView.setQuery(selected, false)

                val bundle = bundleOf("SEARCHED_COUNTRY" to selected)

                findNavController(R.id.nav_host_fragment).navigate(R.id.nav_country_search, bundle)

                //findNavController().navigate(R.id.nav_country, null)
                return true
            }

            override fun onSuggestionSelect(position: Int): Boolean {
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val mc = MatrixCursor(arrayOf(BaseColumns._ID, "country_name"))

                strArrData = dataList.toTypedArray()
                for (i in strArrData.indices) {
                    if (strArrData[i].toLowerCase().startsWith(newText!!.toLowerCase())) mc.addRow(
                        arrayOf<Any>(i, strArrData[i])
                    )
                }
                //strArrData = dataList.toTypedArray()
                cursorAdapter.changeCursor(mc)
                return false
            }
        })
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    override fun onNewIntent(intent: Intent) {
         super.onNewIntent(intent)

        if (Intent.ACTION_SEARCH == intent.action) {
            //val query = intent.getStringExtra(SearchManager.QUERY)
            searchView.clearFocus()
            // User entered text and pressed search button. Perform task ex: fetching data from database and display
        }
     }

    /*override fun onBackPressed() {
       if (backPressedTimer + 2000>System.currentTimeMillis()){
           backToast?.cancel()
           super.onBackPressed()
           return
       }
       else{
           backToast = Toast.makeText(baseContext, "Appuyez encore pour fermer l'application" + SearchManager.QUERY, Toast.LENGTH_SHORT)
           backToast?.show()
       }
       backPressedTimer = System.currentTimeMillis()
    }*/
}
