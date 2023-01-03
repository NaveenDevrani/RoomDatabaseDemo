package com.devcoder.roomdatabasedemo.db.entities

import androidx.room.Embedded
import androidx.room.Relation

data class UserDetails(
    @Embedded
    val contact: Contact,

    @Relation(parentColumn = "id", entityColumn = "contactId")
    val contactInfo: ContactInfo
)
