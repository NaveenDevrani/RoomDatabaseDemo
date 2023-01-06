package com.devcoder.roomdatabasedemo.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devcoder.roomdatabasedemo.ContactsAdapter
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.databinding.FragmentContactListBinding
import com.devcoder.roomdatabasedemo.db.entities.UserDetails
import com.devcoder.roomdatabasedemo.viewmodels.AppViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactListFragment : Fragment(), ContactsAdapter.OnItemClick {
    private var _binding: FragmentContactListBinding? = null
    private val binding: FragmentContactListBinding
        get() = _binding!!
    private val viewModel: AppViewModel by viewModels()
    private lateinit var mAdapter: ContactsAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentContactListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        getContact()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = ContactsAdapter(this@ContactListFragment)
        binding.recyclerview.apply {
            binding.recyclerview.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        handleClicks()
        handleObserver()
    }

    private fun handleClicks() {
        binding.add.setOnClickListener {
            findNavController().navigate(R.id.action_contactListFragment_to_addContactFragment)
        }
    }

    private fun setAdapter(list: List<UserDetails>) {
        mAdapter.submitList(list)
    }

    private fun getContact() {
        viewModel.getUserDetails().observe(viewLifecycleOwner) {
            Log.d("ContactList", "$it")
            setAdapter(it)
        }
    }

    override fun onDelete(model: UserDetails) {
        viewModel.deleteContact(model.contact.id)
    }

    override fun onItemClick(model: UserDetails) {
        findNavController().navigate(R.id.action_contactListFragment_to_showContactDetailsFragment)
    }

    private fun handleObserver() {
        viewModel.deleteObserver.observe(viewLifecycleOwner) {
            getContact()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance() = ContactListFragment()
    }
}