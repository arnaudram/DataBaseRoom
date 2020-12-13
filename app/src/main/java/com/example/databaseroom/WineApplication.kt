        package com.example.databaseroom

import android.app.Application
import timber.log.Timber

class WineApplication:Application() { 
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){

            Timber.plant(Timber.DebugTree())
        }
    }
}