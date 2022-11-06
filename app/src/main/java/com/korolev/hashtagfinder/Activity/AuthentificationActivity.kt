package com.korolev.hashtagfinder.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.korolev.hashtagfinder.R
import com.korolev.hashtagfinder.ViewModel.AuthentificationViewModel


class AuthentificationActivity : AppCompatActivity() {

    private lateinit var etLoginAuth: EditText
    private lateinit var etPasswordAuth:EditText
    private lateinit var btAuth:Button

    private val authentificationViewModel: AuthentificationViewModel by lazy {
        ViewModelProviders.of(this).get(AuthentificationViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentification)

        etLoginAuth = findViewById(R.id.et_login)
        etPasswordAuth = findViewById(R.id.et_password)
        btAuth = findViewById(R.id.bt_auth)


        btAuth.setOnClickListener {
            val login:String = etLoginAuth.text.toString()
            val password:String = etPasswordAuth.text.toString()

            val isEnable = authentificationViewModel.authentificationEqual(login,password)


            if(isEnable) {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)

                finish()
            } else
            {
                Toast.makeText(this,"Неверный логин или пароль!",Toast.LENGTH_SHORT).show()
            }


        }

    }
}