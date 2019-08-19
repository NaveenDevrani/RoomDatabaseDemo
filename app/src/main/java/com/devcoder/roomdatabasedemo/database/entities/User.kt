package com.devcoder.roomdatabasedemo.database.entities

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class User {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int = 0

    @ColumnInfo(name = "name")
    var name: String? = ""

    @ColumnInfo(name = "address")
    var address: String? = ""
}