package com.devcoder.roomdatabasedemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.devcoder.roomdatabasedemo.databinding.FragmentAddContactBinding
import com.devcoder.roomdatabasedemo.db.entities.Contact
import com.devcoder.roomdatabasedemo.db.entities.ContactInfo
import com.devcoder.roomdatabasedemo.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class AddContactFragment : Fragment() {
    private val viewModel: AppViewModel by viewModels()
    private var _binding: FragmentAddContactBinding? = null
    private val binding: FragmentAddContactBinding
        get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAddContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        handleClicks()
        handleObserver()
    }

    private fun handleClicks() {
        binding.buttonSave.setOnClickListener {
            validation()
        }
    }

    private fun handleObserver() {
        viewModel.insertObserver.observe(viewLifecycleOwner) {
            requireActivity().onBackPressedDispatcher.onBackPressed()
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
                val contactInfo = ContactInfo(0, 0, address)
                viewModel.insertContact(contact, contactInfo)
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        @JvmStatic
        fun newInstance() = AddContactFragment()
    }
}