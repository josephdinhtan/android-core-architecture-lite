package com.jddev.androidcorearchlite.app

import android.app.Application
import timber.log.Timber

class CoreArchApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        Timber.plant(AppTree())
        container = AppContainerImpl(this.applicationContext)
    }
}