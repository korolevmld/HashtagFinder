package com.korolev.hashtagfinder.ViewModel

import androidx.lifecycle.ViewModel
import com.korolev.hashtagfinder.HashFinderRepository
import com.korolev.hashtagfinder.data.Users

class AuthentificationViewModel: ViewModel() {

    private val hashFinderRepository: HashFinderRepository = HashFinderRepository.get()

    fun authentificationEqual(login: String, password: String): Boolean {
        val user: Users? = hashFinderRepository.getUser(login)

        return password == user?.password


    }

}