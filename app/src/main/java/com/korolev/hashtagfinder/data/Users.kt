package com.korolev.hashtagfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val login:String,
    var password:String
)
