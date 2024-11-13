package com.jddev.androidcorearchlite.appconfig

import com.jddev.androidcorearchlite.BuildConfig
import javax.inject.Inject

@Suppress("KotlinConstantConditions")
class AppConfig @Inject constructor() {
    fun isDevMode(): Boolean {
        return BuildConfig.DEV_MODE == "dev"
    }

    fun isDebugMode(): Boolean {
        return BuildConfig.DEBUG
    }
}