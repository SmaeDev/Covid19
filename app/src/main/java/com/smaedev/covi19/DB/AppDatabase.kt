package com.smaedev.covi19.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Country::class], version = 3, exportSchema = false)
abstract class AppDatabase() : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    private class CountryDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var countryDao = database.countryDao()
                    countryDao.deleteAll()

                    // Add sample countrie.
                    /*var country = Country("France",15, 155, 170)
                    countryDao.insert(country)

                    country = Country("Chine",19, 195, 170)
                    countryDao.insert(country)

                    country = Country("Italie",15, 155, 170)
                    countryDao.insert(country)

                    country = Country("Sénégal",15, 155, 170)
                    countryDao.insert(country)

                    country = Country("Togo",15, 155, 170)
                    countryDao.insert(country)*/

                }
            }
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context?, scope: CoroutineScope): AppDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    AppDatabase::class.java,
                    "taabour_database"

                ).addCallback(CountryDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }
}