package com.devcoder.roomdatabasedemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devcoder.roomdatabasedemo.databinding.ItemContactBinding
import com.devcoder.roomdatabasedemo.db.entities.Contact

class ContactsAdapter(private val callBack: OnItemClick) : ListAdapter<Contact, ContactsAdapter.ViewHolder>(ContactDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

//    override fun getItemCount() = list.size

    inner class ViewHolder(private val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {


        fun bind(model: Contact) {
            binding.tvName.text = model.name
            binding.tvPhone.text = model.phone
            binding.tvFirstLetter.text = model.name?.first().toString()

            binding.root.setOnLongClickListener {
                callBack.onDelete(model)
                true
            }
        }
    }

    interface OnItemClick {
        fun onDelete(model: Contact)
    }

    class ContactDiffUtil : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }

}