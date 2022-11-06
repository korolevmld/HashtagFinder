package com.korolev.hashtagfinder.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.korolev.hashtagfinder.data.HashtagInfo
import com.korolev.hashtagfinder.data.Post
import com.korolev.hashtagfinder.data.Users
import java.util.*

@Dao
interface Dao {

    //Users
    @Query("SELECT COUNT(*) FROM Users")
    fun getCountUser():Int?

    @Query("SELECT * FROM Users where login=(:login)")
    fun getUser(login:String): Users?

    @Query("INSERT INTO Users(login,password) VALUES(:login,:password)")
    fun insertUser(login:String,password:String)


    //HashtagInfo
    @Query("INSERT INTO HashtagInfo(hashtagName,serviceName) VALUES(:hashtagName,:serviceName)")
    fun insertHashtag(hashtagName:String,serviceName:String)

    @Query("SELECT hashtagName,serviceName FROM HashtagInfo")
    fun getAllHashtag():List<com.korolev.hashtagfinder.model.HashtagInfo?>

    @Query("DELETE FROM HashtagInfo where hashtagName=(:hashtagName) and serviceName=(:serviceName)")
    fun deleteHashtag(hashtagName: String,serviceName: String)



    //Post
    @Query("INSERT INTO Post(hashtagName,text,url) VALUES(:hashtagName,:text,:url)")
    fun insertPost(hashtagName:String,text:String,url:String)

    @Query("SELECT id,hashtagName,text,url FROM Post")
    fun getAllPost():List<Post?>

    @Query("SELECT id,hashtagName,text,url FROM Post where hashtagName=(:hashtagName)")
    fun getPostFromHashtagName(hashtagName: String):List<Post?>


    @Query("DELETE FROM Post")
    fun deleteAllPost()


}