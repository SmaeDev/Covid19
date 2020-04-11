package com.smaedev.covid19

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.CursorAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.firebase.auth.FirebaseAuth
import com.smaedev.covid19.Connect.LoginActivity
import com.smaedev.covid19.databinding.ActivityMainBinding
import com.smaedev.covid19.repository.dataList
class MainActivity : AppCompatActivity() {

    private var strArrData = arrayOf("No Suggestions")

    private lateinit var cursorAdapter: SimpleCursorAdapter
    private lateinit var searchView: SearchView
    lateinit var binding: ActivityMainBinding
    lateinit var signOutButton : Button

    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object {
        private var INSTANCE: MainActivity? = null

        fun mainContext() : Context? {
            return INSTANCE?.baseContext
        }

        fun getLaunchIntent(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }

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

        val headerView: View = navView.getHeaderView(0)
        //val signOutButton : Button = headerView.findViewById(R.id.sign_out_button)
        signOutButton = headerView.findViewById(R.id.sign_out_button)
        setupUI()
    }

    private fun setupUI() {
        signOutButton.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        startActivity(LoginActivity.getLaunchIntent(this))
        FirebaseAuth.getInstance().signOut();
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
