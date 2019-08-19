package com.devcoder.roomdatabasedemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.database.AppDatabase
import com.devcoder.roomdatabasedemo.database.daos.UserDao
import com.devcoder.roomdatabasedemo.database.entities.User

class UserAdapter(var context: Context) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var userList: List<User>? = null
    var userDao:UserDao?=null
    var appDatabase:AppDatabase?=null

//    init {
//        appDatabase= AppDatabase.getInstance(context)
//        userList= appDatabase!!.userDao().getUserData()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(context).inflate(R.layout.user_adapter_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (userList != null) userList!!.size else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (userList != null) {
            var user: User = userList!![position]
            holder.tv_name.text = user.name
            holder.tv_address.text = user.address
        } else
            holder.tv_name.text = "no data found"

    }

    fun setUserData(list: List<User>) {
        userList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_name)
        val tv_address = itemView.findViewById<TextView>(R.id.tv_address)
    }
}