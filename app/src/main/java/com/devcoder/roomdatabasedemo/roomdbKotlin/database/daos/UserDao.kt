package com.devcoder.roomdatabasedemo.roomdbKotlin.database.daos

import androidx.lifecycle.LiveData
import androidx.room.*
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

//    @Query("UPDATE User Where id=id ")
//    fun upateUserInfo(id:Int,name:String,address:String)


    @Update
    fun updateUserData(user: User): Int

}