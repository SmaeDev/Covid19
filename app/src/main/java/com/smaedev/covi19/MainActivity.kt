package com.smaedev.covi19

import android.app.SearchManager
import android.content.Context
import android.database.MatrixCursor
import android.os.Bundle
import android.provider.BaseColumns
import android.view.Menu
import android.widget.CursorAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.cursoradapter.widget.SimpleCursorAdapter
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    /*private var backPressedTimer: Long = 0
    private var backToast: Toast? = null*/


    private val strArrData = arrayOf("No Suggestions")
    private lateinit var cursorAdapter: SimpleCursorAdapter


    private lateinit var appBarConfiguration: AppBarConfiguration

    companion object {
        private var INSTANCE: MainActivity? = null

        fun mainActivityContext() : Context? {
            return INSTANCE?.baseContext
        }
    }
    init {
        INSTANCE = this
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


        val from = arrayOf("country_name")
        val to = intArrayOf(android.R.id.text1)

        // setup SimpleCursorAdapter
        cursorAdapter = SimpleCursorAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item,null,from,to,
            CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        )
    }

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.isIconified = false
        searchView.suggestionsAdapter = cursorAdapter

        searchView.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
            override fun onSuggestionClick(position: Int): Boolean {
                // Add clicked text to search box
                val ca: androidx.cursoradapter.widget.CursorAdapter? = searchView.suggestionsAdapter
                val cursor = ca?.cursor
                cursor?.moveToPosition(position)
                val selected = cursor?.getString(cursor.getColumnIndex("country_name"))

                searchView.setQuery(selected, false)

                //findNavController().navigate(R.id.nav_country, null)

                return true
            }

            override fun onSuggestionSelect(position: Int): Boolean {
                return true
            }
        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query : String?): Boolean{
                /*searchView.clearFocus()
                searchView.setQuery("", false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity, "Recherche $query", Toast.LENGTH_LONG).show()*/
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                val mc = MatrixCursor(arrayOf(BaseColumns._ID, "country_name"))
                for (i in strArrData.indices) {
                    if (strArrData[i].toLowerCase().startsWith(newText!!.toLowerCase())) mc.addRow(
                        arrayOf<Any>(i, strArrData[i])
                    )
                }
                cursorAdapter.changeCursor(mc)
                Toast.makeText(this@MainActivity, "Recherche $newText", Toast.LENGTH_LONG).show()
                return false
            }
        })
        return true
    }*/

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