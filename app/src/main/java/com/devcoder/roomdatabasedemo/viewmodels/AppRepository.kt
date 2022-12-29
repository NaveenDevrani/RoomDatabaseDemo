package com.devcoder.roomdatabasedemo.viewmodels

import androidx.lifecycle.LiveData
import com.devcoder.roomdatabasedemo.db.dao.ContactDao
import com.devcoder.roomdatabasedemo.db.entities.Contact
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppRepository @Inject constructor(private val contactDao: ContactDao) {

    suspend fun getContacts(): List<Contact> {
        return withContext(Dispatchers.IO) { contactDao.getContact() }
    }

    fun getAllContacts(): LiveData<List<Contact>> {
        return contactDao.getAllContact()
    }

    suspend fun insertContact(contact: Contact): Long {
        return withContext(Dispatchers.IO) { contactDao.insertContact(contact) }
    }

    suspend fun deleteContact(contact: Contact): Int {
        return withContext(Dispatchers.IO) { contactDao.deleteContact(contact) }
    }

    suspend fun deleteContactWithId(id: Long): Int {
        return withContext(Dispatchers.IO) { contactDao.deleteContactWithId(id) }
    }
}