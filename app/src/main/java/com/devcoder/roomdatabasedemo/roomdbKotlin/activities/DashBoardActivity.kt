package com.devcoder.roomdatabasedemo.roomdbKotlin.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.devcoder.roomdatabasedemo.R
import com.devcoder.roomdatabasedemo.roomdbKotlin.adapter.UserAdapter
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.AppDatabase
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.UserviewModel
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.entities.User
import com.devcoder.roomdatabasedemo.roomdbjava.database.AppExecutors
import kotlinx.android.synthetic.main.activity_dash_board.*

class DashBoardActivity : AppCompatActivity(), View.OnClickListener {

    var userList: LiveData<List<User>>? = null
    var mylist: List<User>? = null
    var list: List<User>? = null
    var userdatabase: AppDatabase? = null
    val adapter: UserAdapter? = null
    var mDb: AppDatabase? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)
        setClickListener()
        recyclerView?.layoutManager = LinearLayoutManager(this,RecyclerView.VERTICAL,false)

        mDb = AppDatabase.getInstance(applicationContext)

        val userviewModel = ViewModelProviders.of(this).get(UserviewModel::class.java)
        userviewModel.getUserData()?.observe(this,
            Observer<List<User>> {
                adapter?.setUserData(it)
            })

    }

    override fun onResume() {
        super.onResume()
        retrieveTasks()
    }

    private fun setClickListener() {
        fab?.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.fab -> {
                startActivity(Intent(this, AddUserDataActivity::class.java))
            }
        }
    }

    private fun setDataAdapter() {
            recyclerView?.adapter = mylist?.let { UserAdapter(this, it) }
    }
    private fun retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute {
            val list: List<User>? = mDb?.userDao()?.getAllUser
            runOnUiThread {
                if(list!=null){
                    mylist=list
                    setDataAdapter()
//                    adapter?.setUserData(list)
                }
            adapter?.notifyDataSetChanged()}
        }
    }
}
