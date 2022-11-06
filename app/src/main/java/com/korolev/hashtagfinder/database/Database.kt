package com.korolev.hashtagfinder.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.korolev.hashtagfinder.data.HashtagInfo
import com.korolev.hashtagfinder.data.Post
import com.korolev.hashtagfinder.data.Users

@Database(entities = [Users::class,HashtagInfo::class,Post::class], version = 1, exportSchema = false)
@TypeConverters(TypeConverts::class)
abstract class Database:RoomDatabase() {
    abstract fun dao():Dao
}