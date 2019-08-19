package com.devcoder.roomdatabasedemo.database

import android.app.Application
import android.os.AsyncTask
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.devcoder.roomdatabasedemo.database.daos.UserDao
import com.devcoder.roomdatabasedemo.database.entities.User

class UserviewModel(application: Application) : AndroidViewModel(application) {
    private var TAG: String = this.javaClass.simpleName

    var userDao: UserDao? = null
    var userDatabase: AppDatabase? = null
    var userList: LiveData<List<User>>? = null

    init {
        userDatabase = AppDatabase.getInstance(application)
        userDao = userDatabase?.userDao()
    }

    override fun onCleared() {
        super.onCleared()
        Log.e(TAG, "viewmodel :Destroy")
    }

    fun getUserData(): LiveData<List<User>>? {
        return userList
    }

    fun insertUserData(user: User) {
        InsertAsyncTask(userDao).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, user)
    }

    private class InsertAsyncTask(var userDao: UserDao?) : AsyncTask<User, Unit, Unit>() {

        override fun doInBackground(vararg params: User?): Unit? {
            return userDao?.insertUserData(params[0]!!)
        }

        override fun onPostExecute(result: Unit?) {
            super.onPostExecute(result)

        }
    }
}