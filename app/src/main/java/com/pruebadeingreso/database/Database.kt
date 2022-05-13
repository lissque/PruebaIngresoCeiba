package database

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.pruebadeingreso.database.UserDao
import com.pruebadeingreso.entities.User


@Database(entities = [User::class], version = 1)
abstract class Database : RoomDatabase() {

    private lateinit var instance: database.Database

    abstract fun userDao(): UserDao

    @Synchronized
    fun getInstance(context: Context): database.Database? {
        if (instance == null) {
            instance = Room.databaseBuilder( context.applicationContext,database.Database::class.java, "database")
                .fallbackToDestructiveMigration()
                .build()
        }
        return instance
    }



}