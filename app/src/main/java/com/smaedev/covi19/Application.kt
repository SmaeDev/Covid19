package com.smaedev.covi19

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.smaedev.covi19.db.Country
import com.smaedev.covi19.db.CountryDao
import com.smaedev.covi19.repository.CountryFeed
import kotlinx.coroutines.CoroutineScope
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

    companion object : SingletonHolder<AppDatabase, Context>({
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
    }
}