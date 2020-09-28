package by.bsuir.ivan_bondarau.forum.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import by.bsuir.ivan_bondarau.forum.dao.UserDao
import by.bsuir.ivan_bondarau.forum.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        lateinit var applicationContext: Context
        val Instance: AppDatabase by lazy {
            Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "main-data"
        ).allowMainThreadQueries().build()
        }
    }
}