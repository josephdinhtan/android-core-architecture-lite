package com.jddev.androidcorearchlite.data

import com.jddev.androidcorearchlite.domain.model.ThemeMode
import com.jddev.androidcorearchlite.domain.repository.SettingsRepository

class SettingsRepositoryImpl() : SettingsRepository {
    override suspend fun getThemeMode(): ThemeMode {
        TODO("Not yet implemented")
    }

    override suspend fun getDummyRecord() {
        TODO("Not yet implemented")
    }
}
