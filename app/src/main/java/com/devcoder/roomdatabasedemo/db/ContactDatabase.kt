package com.devcoder.roomdatabasedemo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.devcoder.roomdatabasedemo.db.dao.ContactDao
import com.devcoder.roomdatabasedemo.db.entities.Contact
import com.devcoder.roomdatabasedemo.utils.Converters

@Database(entities = [Contact::class], version = 2)
@TypeConverters(Converters::class)
abstract class ContactDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

//    companion object {
//
//        private val migration_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("ALTER TABLE contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
//            }
//        }
//
//        @Volatile
//        private var database: ContactDatabase? = null
//        fun getDatabase(context: Context): ContactDatabase {
//            if (database == null) {
//                synchronized(this) {
//                    database = Room.databaseBuilder(
//                        context,
//                        ContactDatabase::class.java,
//                        "contactDB"
//                    ).addMigrations(migration_1_2)
//                        .build()
//                }
//            }
//
//            return database!!
//        }
//    }
}
