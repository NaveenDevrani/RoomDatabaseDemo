package com.devcoder.roomdatabasedemo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.devcoder.roomdatabasedemo.databinding.ActivityMainBinding
import com.devcoder.roomdatabasedemo.db.entities.Contact
import com.devcoder.roomdatabasedemo.db.entities.UserDetails
import com.devcoder.roomdatabasedemo.viewmodels.AppViewModel
import com.devcoder.roomdatabasedemo.worker.UpdateWork
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ContactsAdapter.OnItemClick {
    private val viewModel: AppViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter: ContactsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAdapter = ContactsAdapter(this@MainActivity)
        binding.recyclerview.apply {
            binding.recyclerview.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = mAdapter
        }
        handleClicks()
        handleObserver()
    }

    private fun handleClicks() {
        binding.add.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }

    private fun setAdapter(list: List<UserDetails>) {
        mAdapter.submitList(list)
    }

    override fun onResume() {
        super.onResume()
        getContact()
    }

    private fun getContact() {
//        viewModel.getContact().observe(this) {
//            Log.d("ContactList", "$it")
//            setAdapter(it)
//        }
        viewModel.getUserDetails().observe(this) {
            Log.d("ContactList", "$it")
            setAdapter(it)
        }
    }

    override fun onDelete(model: UserDetails) {
        viewModel.deleteContact(model.contact.id)
    }

    private fun handleObserver() {
        viewModel.deleteObserver.observe(this) {
            getContact()
        }
    }
}