package com.devcoder.roomdatabasedemo.di

import android.content.Context
import androidx.room.Room
import com.devcoder.roomdatabasedemo.db.ContactDatabase
import com.devcoder.roomdatabasedemo.db.dao.ContactDao
import dagger.Binds
import dagger.hilt.android.components.ViewComponent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
 class ViewComponent {

    @Provides
    fun getContactDataBase(@ApplicationContext context: Context): ContactDatabase {
        return Room.databaseBuilder(context, ContactDatabase::class.java, "contactDB").build()
    }

    @Provides
    fun provideChannelDao(appDatabase: ContactDatabase): ContactDao {
        return appDatabase.contactDao()
    }
}