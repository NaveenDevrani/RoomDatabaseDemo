package com.devcoder.roomdatabasedemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.devcoder.roomdatabasedemo.db.entities.Contact

@Dao
interface ContactDao {

    @Insert
    suspend fun insertContact(contact: Contact): Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact): Int

    @Query("DELETE FROM contact WHERE id = :id")
    suspend fun deleteContactWithId(id: Long): Int

    @Query("SELECT *FROM contact")
    fun getAllContact(): LiveData<List<Contact>>

    @Query("SELECT *FROM contact")
    fun getContact(): List<Contact>
}