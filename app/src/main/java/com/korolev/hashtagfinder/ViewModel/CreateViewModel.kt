package com.korolev.hashtagfinder.ViewModel

import androidx.lifecycle.ViewModel
import com.korolev.hashtagfinder.HashFinderRepository

class CreateViewModel:ViewModel() {
    val hashFinderRepository = HashFinderRepository.get()

    fun insertHashtag(hashtagName:String,serviceName:String) {
        hashFinderRepository.insertHashtag(hashtagName, serviceName)
    }
}