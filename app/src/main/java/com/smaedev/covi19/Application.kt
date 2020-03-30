package com.smaedev.covi19

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.db.CountryDao
import com.smaedev.covi19.repository.CountryFeed
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.http.GET

class Application: AppCompatActivity() {

    companion object {
        private var INSTANCE: Application? = null
        fun applicationContext() : Context? {
            return INSTANCE?.applicationContext
        }
    }
    init {
        INSTANCE = this
    }
}

//API de recuperation des donn'es
interface ITanApi {
    @GET("cases_by_country.php")
    fun getCountries(): Call<CountryFeed>
}

//Base de donn'es room
@Database(entities = [Country::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao

    private class CountryDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE_BASE?.let { database ->
                scope.launch {
                    //var countryDao = database.countryDao()
                    /*countryDao.deleteAll()*/
                }
            }
        }
    }
    companion object {

        @Volatile
        private var INSTANCE_BASE: AppDatabase? = null

        fun getDatabase(context: Context?, scope: CoroutineScope): AppDatabase {
            return INSTANCE_BASE?: synchronized(this) {
                val instance = Room.databaseBuilder(context!!.applicationContext,
                    AppDatabase::class.java,
                    "covid_database"

                ).addCallback(CountryDatabaseCallback(scope)).build()
                INSTANCE_BASE = instance
                instance
            }
        }

    }

}
