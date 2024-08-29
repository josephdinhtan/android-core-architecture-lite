package com.jddev.androidcorearchlite

import android.content.Context
import com.jddev.androidcorearchlite.features.statemachine.water.WaterManager

interface AppContainer {
    val waterManager: WaterManager
}

class AppContainerImpl(
    private val applicationContext: Context,
) : AppContainer {

    override val waterManager: WaterManager by lazy {
        WaterManager()
    }

    init {
        waterManager
    }
}
