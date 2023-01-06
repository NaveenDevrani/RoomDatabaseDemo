package com.devcoder.roomdatabasedemo.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devcoder.roomdatabasedemo.databinding.FragmentShowContactDetailsBinding
import com.devcoder.roomdatabasedemo.viewmodels.AppViewModel

private const val CONTACT_ID = "param1"

class ShowContactDetailsFragment : Fragment() {
    private var contactId: String? = null
    private var _binding: FragmentShowContactDetailsBinding? = null
    private val binding: FragmentShowContactDetailsBinding
        get() = _binding!!
    private val viewModel: AppViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contactId = it.getString(CONTACT_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentShowContactDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(contactId: String) =
            ShowContactDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTACT_ID, contactId)
                }
            }
    }

    private fun handleObserver() {
        viewModel.getContact()
    }
}