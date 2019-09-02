package com.devcoder.roomdatabasedemo.roomdbKotlin.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.devcoder.roomdatabasedemo.roomdbKotlin.database.entities.User

@Dao
interface UserDao {

    @Insert
    fun insertUserData(user: User)

    @Query("SELECT *FROM User")
    fun getUserData(): LiveData<List<User>>

    @Query("SELECT *FROM User")
    fun getUserAll(): List<User>

    @Delete
    fun delete(user: User)

    @get:Query(value = "SELECT * FROM User")
    val getAllUser: List<User>
}