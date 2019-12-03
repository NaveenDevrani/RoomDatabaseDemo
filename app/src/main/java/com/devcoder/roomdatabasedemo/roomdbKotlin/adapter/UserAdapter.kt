package com.devcoder.roomdatabasedemo.roomdbKotlin.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.roomdbKotlin.activities.AddUserDataActivity
import com.devcoder.roomdatabasedemo.roomdbKotlin.activities.DashBoardActivity
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.AppDatabase
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.entities.User
import com.devcoder.roomdatabasedemo.roomdbjava.database.AppExecutors

class UserAdapter(var context: Context, var userList: List<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    var alertDialog: AlertDialog? = null
    var appDatabase: AppDatabase? = null

    init {
        appDatabase = AppDatabase.getInstance(context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.user_adapter_view, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user: User = userList[position]
        holder.tv_name.text = user.name
        holder.tv_address.text = user.address
        holder.cardView.setOnClickListener {
            val intent = Intent(context, AddUserDataActivity::class.java)
            intent.action = "update"
            intent.putExtra("id", user.id)
            intent.putExtra("name", user.name)
            intent.putExtra("address", user.address)
            context.startActivity(intent)
        }

        holder.cardView.setOnLongClickListener {
            showDeleteConfirmationAlert(user)
            return@setOnLongClickListener true
        }
    }

    fun setUserData(list: List<User>) {
        userList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardView = itemView.findViewById<CardView>(R.id.cardView)
        val tv_name = itemView.findViewById<TextView>(R.id.tv_name)
        val tv_address = itemView.findViewById<TextView>(R.id.tv_address)
    }

    fun showDeleteConfirmationAlert(user: User) {

        val builder = AlertDialog.Builder(context)
        val views =
            LayoutInflater.from(context).inflate(R.layout.delete_confirmation_alert, null, false)


        views.findViewById<Button>(R.id.button_delete)?.setOnClickListener {
            AppExecutors.getInstance().diskIO().execute {
                appDatabase?.userDao()?.delete(user)
                (context as DashBoardActivity).retrieveTasks()
            }
            if (alertDialog != null && alertDialog!!.isShowing)
                alertDialog?.dismiss()
        }

        views.findViewById<Button>(R.id.button_cancel)?.setOnClickListener {
            if (alertDialog != null && alertDialog!!.isShowing)
                alertDialog?.dismiss()
        }
        builder.setView(views)
        alertDialog = builder.create()
        alertDialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(alertDialog!!.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        alertDialog!!.window!!.attributes = lp
        alertDialog!!.setCancelable(true)
        alertDialog!!.show()
    }

}