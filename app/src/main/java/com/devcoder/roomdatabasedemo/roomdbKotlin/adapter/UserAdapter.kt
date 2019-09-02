package com.devcoder.roomdatabasedemo.roomdbKotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.AppDatabase
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.daos.UserDao
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.entities.User

class UserAdapter(var context: Context, var userList: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

//    var userList: List<User> = ArrayList()
    var userDao:UserDao?=null
    var appDatabase:AppDatabase?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_adapter_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = userList!![position]
        holder.tv_name.text = user.name
        holder.tv_address.text = user.address
    }

    fun setUserData(list: List<User>) {
        userList=list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_name)
        val tv_address = itemView.findViewById<TextView>(R.id.tv_address)
    }
}