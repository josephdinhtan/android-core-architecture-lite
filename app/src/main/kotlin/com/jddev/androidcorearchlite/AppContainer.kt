package com.jddev.androidcorearchlite

import android.content.Context
import com.jddev.androidcorearchlite.ui.debug.DevUtility
import com.jddev.androidcorearchlite.appconfig.AppConfig
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppContainer @Inject constructor (
    @ApplicationContext val applicationContext: Context,
    val appConfig: AppConfig,
    val devUtility: DevUtility,
)