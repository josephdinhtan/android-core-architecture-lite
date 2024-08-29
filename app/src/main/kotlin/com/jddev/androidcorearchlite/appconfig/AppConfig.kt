package com.jddev.androidcorearchlite.appconfig

import com.jddev.androidcorearchlite.BuildConfig

@Suppress("KotlinConstantConditions")
class AppConfig {
    fun isDevMode(): Boolean {
        return BuildConfig.DEV_MODE == "dev"
    }
}