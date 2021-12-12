package com.example.planetforme

import android.app.Application

class PlanetForMeApplication:Application() {

    override fun onCreate() {
        super.onCreate()
        UserRepository.initialize(this)
    }

}