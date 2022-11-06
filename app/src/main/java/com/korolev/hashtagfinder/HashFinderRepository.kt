package com.korolev.hashtagfinder

import android.content.Context
import androidx.room.Room
import com.korolev.hashtagfinder.data.Post
import com.korolev.hashtagfinder.data.Users
import com.korolev.hashtagfinder.database.Database


private const val DATABASE_NAME = "hashfinder-database"

class HashFinderRepository private constructor(context: Context){

    private val database: Database = Room.databaseBuilder(
        context.applicationContext,
        Database::class.java,
        DATABASE_NAME
    ).allowMainThreadQueries().build()

    private val dao = database.dao()

    //Users
    fun getCountUser(): Int? = dao.getCountUser()
    fun getUser(login:String): Users? = dao.getUser(login)
    fun insertUser(login:String,password:String) = dao.insertUser(login,password)

    //HashtagInfo
    fun insertHashtag(hashtagName:String,serviceName:String) = dao.insertHashtag(hashtagName, serviceName)
    fun getAllHashtag():List<com.korolev.hashtagfinder.model.HashtagInfo?> = dao.getAllHashtag()
    fun deleteHashtag(hashtagName:String,serviceName:String) = dao.deleteHashtag(hashtagName, serviceName)

    //Post
    fun insertPost(hashtagName: String,text:String,url:String) = dao.insertPost(hashtagName,text,url)
    fun getAllPost():List<Post?> = dao.getAllPost()
    fun deleteAllPost() = dao.deleteAllPost()
    fun getPostFromHashtagName(hashtagName: String):List<Post?> = dao.getPostFromHashtagName(hashtagName)

    companion object {
       private var INSTANCE: HashFinderRepository? = null

       fun initialize(context: Context) {
           if(INSTANCE==null)
           {
              INSTANCE = HashFinderRepository(context)
           }
       }

       fun get():HashFinderRepository {
           return INSTANCE?:
           throw IllegalStateException("HashFinderRepository must be initialized")
       }


    }

}