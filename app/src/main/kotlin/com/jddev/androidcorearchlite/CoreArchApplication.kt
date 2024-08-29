package com.jddev.androidcorearchlite

import android.app.Application
import com.jddev.androidcorearchlite.appconfig.AppTree
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CoreArchApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        Timber.plant(AppTree())
        container = AppContainerImpl(this.applicationContext)
    }
}