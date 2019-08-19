package com.devcoder.roomdatabasedemo.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.database.AppDatabase
import com.devcoder.roomdatabasedemo.database.UserviewModel
import com.devcoder.roomdatabasedemo.database.entities.User
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class AddUserDataActivity : AppCompatActivity(), View.OnClickListener {

    var userviewModel: UserviewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setClickListener()
        userviewModel = ViewModelProviders.of(this).get(UserviewModel::class.java)

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
            TextUtils.isEmpty(name) -> Toast.makeText(this, "please enter your name", Toast.LENGTH_SHORT).show()
            TextUtils.isEmpty(address) -> Toast.makeText(this, "please enter your address", Toast.LENGTH_SHORT).show()
            else -> {
                val user = User()
                user.name = name
                user.address = address
                userviewModel?.insertUserData(user)
                onBackPressed()
            }
        }
    }

    private fun setClickListener() {
        btnSubmit.setOnClickListener(this)
    }
}
