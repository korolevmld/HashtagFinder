package com.korolev.hashtagfinder.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.korolev.hashtagfinder.R
import com.korolev.hashtagfinder.ViewModel.RegistrationViewModel



class RegistrationActivity : AppCompatActivity() {

    private lateinit var btRegistration: Button
    private lateinit var etLogin:EditText
    private lateinit var etPassword:EditText
    private lateinit var etPasswordCheck:EditText

    private val registrationViewMode: RegistrationViewModel by lazy {
        ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        btRegistration = findViewById(R.id.bt_registration)
        etLogin = findViewById(R.id.et_login)
        etPassword = findViewById(R.id.et_password)
        etPasswordCheck = findViewById(R.id.et_password_check)

        btRegistration.setOnClickListener {
            val login = etLogin.text.toString()
            val password = etPassword.text.toString()
            val passwordCheck = etPasswordCheck.text.toString()

            if(login =="")
            {
                Toast.makeText(this,"Введите логин!",Toast.LENGTH_SHORT).show()

            }
            else if(password=="")
            {
                Toast.makeText(this,"Введите пароль!",Toast.LENGTH_SHORT).show()
            }
            else
            {

            if(registrationViewMode.checkRepeatPassword(password,passwordCheck))
            {
                registrationViewMode.insertDataToDatabase(login, password)
                val intent = Intent(this,AuthentificationActivity::class.java)
                startActivity(intent)

                finish()
            }
            else
            {
                Toast.makeText(this,"Пароли не совпадают!", Toast.LENGTH_SHORT).show()
            }
            }
        }


    }





}