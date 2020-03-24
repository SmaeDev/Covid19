package com.smaedev.covi19

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.widget.CursorAdapter
import android.widget.SimpleCursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.gson.GsonBuilder
import com.smaedev.covi19.Adapter.FragDetailCountry
import com.smaedev.covi19.ui.country.CountryFragment
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private var backPressedTimer: Long = 0
    private var backToast: Toast? = null


    private lateinit var appBarConfiguration: AppBarConfiguration
    var searchView: SearchView? = null
    private var myAdapter: SimpleCursorAdapter? = null

    companion object {
        private var instance: MainActivity? = null
        fun applicationContext() : Context? {
            return instance?.applicationContext
        }
    }
    init {
        instance = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        /*val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }*/
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_country, R.id.nav_about), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        val from = arrayOf("libelle")
        val to = intArrayOf(android.R.id.text1)

        // setup SimpleCursorAdapter
        myAdapter = SimpleCursorAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item,null,from,to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

   /* override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            if (searchView != null) {
                searchView!!.clearFocus()
                Toast.makeText(baseContext, "bien" + SearchManager.QUERY, Toast.LENGTH_SHORT).show()
            }
        }
    }*/

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

class HomeFeed(val countries_stat : List<Country>)
class Country(val country_name: String, val cases: String)

interface IOnBackPressed {
    fun onBackPressed(): Boolean
}
