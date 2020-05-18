package com.smaedev.covid19

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.smaedev.covid19.db.Advice
import com.smaedev.covid19.db.AdviceDao
import com.smaedev.covid19.db.Country
import com.smaedev.covid19.db.CountryDao

class ApplicationApp: Application() {

    companion object {
        private var INSTANCE: Application? = null
        lateinit var firebase_db: DatabaseReference

        fun applicationContext() : Context? {
            return INSTANCE?.applicationContext
        }
    }
    init {
        INSTANCE = this
    }

    override fun onCreate() {
        super.onCreate()

        firebase_db = FirebaseDatabase.getInstance().reference
        firebase_db.setValue("C'est ok")
    }
}

//DB room
@Database(entities = [Country::class, Advice::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
    abstract fun adviceDao(): AdviceDao

    /*companion object {

        @Volatile
        private var appDatabase: AppDatabase? = null

        fun getDatabase(context: Context?, scope: CoroutineScope): AppDatabase {

            return appDatabase ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context!!.applicationContext,
                    AppDatabase::class.java,
                    "covid_database"

                ).addCallback(AdviceDatabaseCallback(scope)).allowMainThreadQueries().build()
                appDatabase = instance
                instance
            }
        }

    private class AdviceDatabaseCallback(
        private val scope: CoroutineScope) : Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            appDatabase?.let { database ->
                scope.launch {
                var adviceDao = database.adviceDao()

                // Add advice.
                var advice = Advice("eva", "maeva", "esta", "esa")
                adviceDao.insert(advice)


                Log.d("Advicesddgg ///////","Adv ${adviceDao.getAlphabetizedAdvices()}")

            }
            }
        }
    }*/

    companion object {
        var database = Room.databaseBuilder(
            ApplicationApp.applicationContext()!!,
            AppDatabase::class.java,
            "covid19_db"
        ).allowMainThreadQueries().build()
        //).build()*
    }

   /* companion object : SingletonHolder<AppDatabase, Context>({
        Room.databaseBuilder(it.applicationContext, AppDatabase::class.java, "covid19.db").build()
    })

   open class SingletonHolder<T, A>(creator: (A) -> T) {
        private var creator: ((A) -> T)? = creator
        @Volatile private var instance: T? = null

        fun getInstance(arg: A): T {
            val i = instance
            if (i != null) {
                return i
            }

            return synchronized(this) {
                val i2 = instance
                if (i2 != null) {
                    i2
                } else {
                    val created = creator!!(arg)
                    instance = created
                    creator = null
                    created
                }
            }
        }
    }*/
}




