package com.jddev.androidcorearchlite.app

import android.content.Context
import android.nfc.NfcManager
import com.jddev.androidcorearchlite.data.SettingsRepository
import com.jddev.androidcorearchlite.data.SettingsRepositoryImpl

interface AppContainer {
    val settingsRepository: SettingsRepository
}

class AppContainerImpl(
    private val applicationContext: Context,
) : AppContainer {

    override val settingsRepository: SettingsRepository by lazy {
        SettingsRepositoryImpl()
    }
}
