package com.devcoder.roomdatabasedemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devcoder.roomdatabasedemo.db.entities.Contact
import com.devcoder.roomdatabasedemo.db.entities.ContactInfo
import com.devcoder.roomdatabasedemo.db.entities.UserDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val repo: AppRepository) : ViewModel() {

    val insertObserver: MutableLiveData<Boolean> = MutableLiveData()
    val deleteObserver: MutableLiveData<Boolean> = MutableLiveData()
    fun insertContact(contact: Contact, contactInfo: ContactInfo) {
        viewModelScope.launch {
            val id = repo.insertContactAndInfo(contact, contactInfo)
            insertObserver.postValue(id > 0)
        }
    }

    fun getContact(): LiveData<List<Contact>> {
        return repo.getAllContacts()
    }

    fun getUserDetails(): LiveData<List<UserDetails>> {
        return repo.getAllUserDetails()
    }

    fun deleteContact(id: Long) {
        viewModelScope.launch {
            val isDelete = repo.deleteContactWithId(id)
            deleteObserver.postValue(isDelete > 0)
        }
    }
}