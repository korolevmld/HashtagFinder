package com.korolev.hashtagfinder.ViewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.korolev.hashtagfinder.HashFinderRepository
import kotlinx.coroutines.launch
import java.util.*


class RegistrationViewModel:ViewModel() {

    private val hashFinderRepository: HashFinderRepository = HashFinderRepository.get()


    fun insertDataToDatabase(login:String,password:String) {
        viewModelScope.launch {
            hashFinderRepository.insertUser(login,password)
        }
    }



    fun checkRepeatPassword (password: String,passwordRepeat: String):Boolean {
        return password==passwordRepeat
    }

}