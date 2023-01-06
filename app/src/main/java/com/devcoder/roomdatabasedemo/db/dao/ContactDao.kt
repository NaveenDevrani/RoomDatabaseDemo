package com.devcoder.roomdatabasedemo.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.devcoder.roomdatabasedemo.db.entities.Contact
import com.devcoder.roomdatabasedemo.db.entities.ContactInfo
import com.devcoder.roomdatabasedemo.db.entities.UserDetails

@Dao
interface ContactDao {

    @Insert
    suspend fun insertContact(contact: Contact): Long

    @Insert
    suspend fun insertContactInfo(contact: ContactInfo): Long

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact): Int

    @Query("DELETE FROM contact WHERE id = :id")
    suspend fun deleteContactWithId(id: Long): Int

    @Query("SELECT *FROM contact")
    fun getAllContact(): LiveData<List<Contact>>

    @Transaction
    @Query("SELECT *FROM contact")
    fun getContact(): List<Contact>

    //    @Query("Select *From contact inner join ContactInfo on contactId = ContactInfo.contactId")
//    fun getUserDetails(): LiveData<List<UserDetails>>
    @Transaction
    @Query("Select *From contact")
    fun getUserDetails(): LiveData<List<UserDetails>>

    @Transaction
    @Query("Select *From contact WHERE id= :id")
    fun getUserDetailsById(id: Long): LiveData<List<UserDetails>>
}