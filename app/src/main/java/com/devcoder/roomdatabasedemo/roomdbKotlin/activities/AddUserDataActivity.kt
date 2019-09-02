package com.devcoder.roomdatabasedemo.roomdbKotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.AppDatabase
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.UserviewModel
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.entities.User
import com.devcoder.roomdatabasedemo.roomdbjava.database.AppExecutors
import kotlinx.android.synthetic.main.activity_add_user.*

class AddUserDataActivity : AppCompatActivity(), View.OnClickListener {

    var userviewModel: UserviewModel? = null
    var appDatabase:AppDatabase?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        setClickListener()
        userviewModel = ViewModelProviders.of(this).get(UserviewModel::class.java)
        appDatabase= AppDatabase.getInstance(applicationContext)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btnSubmit -> {
                handleSubmit()
            }
            R.id.fab -> {
                startActivity(Intent(this, AddUserDataActivity::class.java))
            }
        }
    }

    private fun handleSubmit() {
        val name = et_name.text.toString()
        val address = et_address.text.toString()
        when {
            TextUtils.isEmpty(name) -> Toast.makeText(
                this,
                "please enter your name",
                Toast.LENGTH_SHORT
            ).show()
            TextUtils.isEmpty(address) -> Toast.makeText(
                this,
                "please enter your address",
                Toast.LENGTH_SHORT
            ).show()
            else -> {
                val user = User()
                user.name = name
                user.address = address
                AppExecutors.getInstance().diskIO().execute {
                    appDatabase?.userDao()?.insertUserData(user)
                    finish()
                }
            }
        }
    }

    private fun setClickListener() {
        btnSubmit.setOnClickListener(this)
    }
}
