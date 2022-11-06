package com.korolev.hashtagfinder.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.korolev.hashtagfinder.HashFinderRepository

class EmptyActivityForLaunch : AppCompatActivity() {

    private val hashFinderRepository: HashFinderRepository = HashFinderRepository.get()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userCheck:Int? = hashFinderRepository.getCountUser()
        if(userCheck != null && userCheck>0)
        {
            val intent = Intent(this,AuthentificationActivity::class.java)
            startActivity(intent)
        }
        else
        {
            val intent = Intent(this,RegistrationActivity::class.java)
            startActivity(intent)
        }

        finish()
    }
}