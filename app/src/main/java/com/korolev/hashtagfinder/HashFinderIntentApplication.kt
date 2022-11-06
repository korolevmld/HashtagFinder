package com.korolev.hashtagfinder

import android.app.Application

class HashFinderIntentApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        HashFinderRepository.initialize(this)
    }

}