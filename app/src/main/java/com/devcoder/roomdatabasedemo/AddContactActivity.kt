package com.devcoder.roomdatabasedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.devcoder.roomdatabasedemo.databinding.ActivityAddContactBinding
import com.devcoder.roomdatabasedemo.db.entities.Contact
import com.devcoder.roomdatabasedemo.db.entities.ContactInfo
import com.devcoder.roomdatabasedemo.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date

@AndroidEntryPoint
class AddContactActivity : AppCompatActivity() {
    private val viewModel: AppViewModel by viewModels()
    private lateinit var binding: ActivityAddContactBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleClicks()
        handleObserver()
    }

    private fun handleClicks() {
        binding.buttonSave.setOnClickListener {
            validation()
        }
    }

    private fun handleObserver() {
        viewModel.insertObserver.observe(this) {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun validation() {
        binding.apply {
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val address: String = etAddress.text.toString()
            if (name.isEmpty()) {
                etName.error = "*required"
            } else if (phone.isEmpty()) {
                etPhone.error = "*required"
            } else if (address.isEmpty()) {
                etAddress.error = "*required"
            } else {
                val contact = Contact(0, name, phone, Date())
                val contactInfo = ContactInfo(0,0, address)
                viewModel.insertContact(contact, contactInfo)
            }
        }

    }
}