package com.devcoder.roomdatabasedemo.roomdbKotlin.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.daos.UserDao
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.entities.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val Tag:String="AppDatabase"
        private var sInstance: AppDatabase? = null

            @Synchronized
            fun getInstance(context: Context): AppDatabase {
                if (sInstance == null) {
                    try {
                        sInstance = Room
                            .databaseBuilder(context.applicationContext, AppDatabase::class.java, "userdatabase.db")
                            .fallbackToDestructiveMigration()
                            .build()
                    }catch (e:Exception){
                        Log.e(Tag,"${e.message}")
                        e.printStackTrace()
                    }
                }
                return sInstance!!
            }
    }
}