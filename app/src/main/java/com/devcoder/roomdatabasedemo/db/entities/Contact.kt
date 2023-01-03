package com.devcoder.roomdatabasedemo.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "contact")
data class Contact(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "phone") val phone: String?,
    @ColumnInfo(name = "date") val date: Date?,
    @ColumnInfo(name = "isActive") val isActive: Int=0
)