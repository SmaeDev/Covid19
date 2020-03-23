package com.smaedev.covi19

import android.app.SearchManager
import android.content.Context
import android.content.Intent
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
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    var searchView: SearchView? = null
    private var myAdapter: SimpleCursorAdapter? = null

    companion object {
        var fragment: FragmentManager? = null
        private var instance: MainActivity? = null
        fun applicationContext() : Context? {
            return instance?.applicationContext
        }
    }
    init {
        instance = this
        fragment = supportFragmentManager
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*recyclerviewCountry.layoutManager = LinearLayoutManager(this)
        fetchJson()*/

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

        // Get Search item from action bar and Get Search service

        // Get Search item from action bar and Get Search service
        /*val searchItem = menu.findItem(R.id.action_search)
        val searchManager = this@MainActivity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }
        if (searchView != null) {
            searchView!!.setSearchableInfo(searchManager.getSearchableInfo(this@MainActivity.componentName))
            searchView!!.isIconified = false
            searchView!!.suggestionsAdapter = myAdapter
            // Getting selected (clicked) item suggestion
            searchView!!.setOnSuggestionListener(object : OnSuggestionListener() {
                fun onSuggestionClick(position: Int): Boolean {
                    // Add clicked text to search box
                    val ca: CursorAdapter = searchView!!.suggestionsAdapter
                    val cursor: Cursor = ca.getCursor()
                    cursor.moveToPosition(position)
                    val selected = cursor.getString(cursor.getColumnIndex("libelle"))
                    searchView!!.setQuery(selected, false)
                    val bundle = Bundle()
                    bundle.putString("sousproduit", selected)
                    val fragSousProduit = FragSousProduit()
                    fragSousProduit.setArguments(bundle)
                    MainActivity.fragmentManager.beginTransaction()
                        .replace(R.id.fragContainer, fragSousProduit).addToBackStack(null).commit()
                    return true
                }

                fun onSuggestionSelect(position: Int): Boolean {
                    return true
                }
            })
            searchView!!.setOnQueryTextListener(object : OnQueryTextListener() {
                fun onQueryTextSubmit(s: String?): Boolean {
                    return false
                }

                fun onQueryTextChange(s: String): Boolean {

                    // Filter data
                    val mc =
                        MatrixCursor(arrayOf(BaseColumns._ID, "libelle"))
                    for (i in strArrData.indices) {
                        if (strArrData.get(i).toLowerCase().startsWith(s.toLowerCase())) mc.addRow(
                            arrayOf<Any>(i, strArrData.get(i))
                        )
                    }
                    myAdapter?.changeCursor(mc)
                    return false
                }
            })
        }*/

        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    fun fetchJson(){
        println("Test")
        val request= Request.Builder()
            .url("https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php")
            .get()
            .addHeader("x-rapidapi-host", "coronavirus-monitor.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "2547575680msh1aee1093c9acb63p1f21b9jsn5ba8aa6b9d5e")
            .build()
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val body = response?.body?.string()
                println("ok"+body)

                val gson = GsonBuilder().create()
                val homeFeed = gson.fromJson(body, HomeFeed::class.java)

                runOnUiThread(){
                    ///recyclerviewCountry.adapter = MainAdapter(homeFeed)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            if (searchView != null) {
                searchView!!.clearFocus()
                Toast.makeText(baseContext, "bien" + SearchManager.QUERY, Toast.LENGTH_SHORT).show()
            }

            // User entered text and pressed search button. Perform task ex: fetching data from database and display
        }
    }
}

class HomeFeed(val countries_stat : List<Country>)
class Country(val country_name: String, val cases: String)