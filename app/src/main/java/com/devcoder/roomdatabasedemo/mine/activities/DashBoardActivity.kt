package com.devcoder.roomdatabasedemo.mine.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.mine.adapter.UserAdapter
import com.devcoder.roomdatabasedemo.mine.database.AppDatabase
import com.devcoder.roomdatabasedemo.mine.database.UserviewModel
import com.devcoder.roomdatabasedemo.mine.database.entities.User
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity(), View.OnClickListener {

    var userList: LiveData<List<User>>? = null
    var list: List<User>? = null
    var userdatabase: AppDatabase? = null
    val adapter: UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        setClickListener()
        recyclerView?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        setDataAdapter()
        val userviewModel = ViewModelProviders.of(this).get(UserviewModel::class.java)
        userviewModel.getUserData()?.observe(this,
            Observer<List<User>> {
                adapter?.setUserData(it)
            })

    }

    override fun onResume() {
        super.onResume()
        adapter?.notifyDataSetChanged()
    }

    private fun setClickListener() {
        fab.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab -> {
                startActivity(Intent(this, AddUserDataActivity::class.java))
            }
        }
    }

    private fun setDataAdapter() {
        if (userList != null)
            recyclerView.adapter = UserAdapter(this)
    }
}
