package com.devcoder.roomdatabasedemo

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devcoder.roomdatabasedemo.databinding.ItemContactBinding
import com.devcoder.roomdatabasedemo.db.entities.Contact
import com.devcoder.roomdatabasedemo.db.entities.UserDetails
import java.util.*

class ContactsAdapter(private val callBack: OnItemClick) : ListAdapter<UserDetails, ContactsAdapter.ViewHolder>(ContactDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(model: UserDetails) {
            binding.apply {
                binding.tvName.text = model.contact.name
                binding.tvPhone.text = model.contact.phone
                binding.tvAddress.text = model.contactInfo.address
                binding.tvFirstLetter.text = model.contact.name?.first()?.uppercase().toString()
                binding.tvFirstLetter.backgroundTintList = ColorStateList.valueOf(getRandomColor())
                binding.root.setOnLongClickListener {
                    callBack.onDelete(model)
                    true
                }
                root.setOnClickListener {
                    callBack.onItemClick(model)
                }
            }
        }
    }

    interface OnItemClick {
        fun onDelete(model: UserDetails)
        fun onItemClick(model: UserDetails)
    }

    class ContactDiffUtil : DiffUtil.ItemCallback<UserDetails>() {
        override fun areItemsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem.contact.id == newItem.contact.id
        }

        override fun areContentsTheSame(oldItem: UserDetails, newItem: UserDetails): Boolean {
            return oldItem == newItem
        }
    }

    private fun getRandomColor(): Int {
        val rnd = Random()
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }

}