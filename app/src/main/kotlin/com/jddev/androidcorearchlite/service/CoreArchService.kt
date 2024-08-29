package com.jddev.androidcorearchlite.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import timber.log.Timber

class CoreArchService : Service() {
    override fun onCreate() {
        super.onCreate()
        Timber.d("onCreate")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Timber.d("onStartCommand")

        // auto restart by system when low memory resources
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        Timber.e("onBind not supported")
        return null
    }
}