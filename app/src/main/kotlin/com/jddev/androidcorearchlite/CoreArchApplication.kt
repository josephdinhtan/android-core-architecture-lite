package com.jddev.androidcorearchlite

import android.app.Application
import com.simpletouch.utils.logging.AppTree
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CoreArchApplication : Application() {
    @Inject
    lateinit var appTree: AppTree

    @Inject
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
    }
}