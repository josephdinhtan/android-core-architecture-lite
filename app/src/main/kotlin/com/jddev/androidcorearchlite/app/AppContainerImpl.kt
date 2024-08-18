package com.jddev.androidcorearchlite.app

import android.content.Context
import com.jddev.androidcorearchlite.data.SettingsRepository
import com.jddev.androidcorearchlite.data.SettingsRepositoryImpl
import com.jddev.androidcorearchlite.features.statemachine.water.WaterManager

interface AppContainer {
    val settingsRepository: SettingsRepository
    val waterManager: WaterManager
}

class AppContainerImpl(
    private val applicationContext: Context,
) : AppContainer {

    override val settingsRepository: SettingsRepository by lazy {
        SettingsRepositoryImpl()
    }

    override val waterManager: WaterManager by lazy {
        WaterManager()
    }

    init {
        settingsRepository
        waterManager
    }
}
