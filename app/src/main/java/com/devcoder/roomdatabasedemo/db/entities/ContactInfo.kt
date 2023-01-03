package com.devcoder.roomdatabasedemo.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "ContactInfo",
    foreignKeys = [ForeignKey(
        entity = Contact::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("contactId"), onDelete = ForeignKey.CASCADE
    )]
)
data class ContactInfo(
    @PrimaryKey(autoGenerate = true) var contactInfoId: Long = 0,
    var contactId: Long = 0,
    @ColumnInfo(name = "address")
    var address: String?
)
