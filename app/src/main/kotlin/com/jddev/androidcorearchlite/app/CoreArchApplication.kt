package com.jddev.androidcorearchlite.app

import android.app.Application

class CoreArchApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this.applicationContext)
    }
}