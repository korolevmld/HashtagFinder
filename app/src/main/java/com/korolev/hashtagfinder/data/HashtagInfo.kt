package com.korolev.hashtagfinder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class HashtagInfo(
    @PrimaryKey(autoGenerate = true)
    val hashtagId: Long,
    val hashtagName: String,
    val serviceName: String
)
