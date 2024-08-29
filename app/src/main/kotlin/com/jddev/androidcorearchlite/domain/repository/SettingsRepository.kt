package com.jddev.androidcorearchlite.domain.repository

import com.jddev.androidcorearchlite.domain.model.ThemeMode

interface SettingsRepository {
    suspend fun getThemeMode(): ThemeMode
    suspend fun getDummyRecord()
}