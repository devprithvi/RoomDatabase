package com.devprithvi.roomdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

//Step 1 :: Creating a Entity Class...

@Entity(tableName = "Contact")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val phone: String,
    val createdDate: Date,
    val isActive: Int //at '0' inActive , at '1'  Active

)
