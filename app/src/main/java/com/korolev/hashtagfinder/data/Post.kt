package com.korolev.hashtagfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Post(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val hashtagName: String,
    val text: String,
    val url:String
)
