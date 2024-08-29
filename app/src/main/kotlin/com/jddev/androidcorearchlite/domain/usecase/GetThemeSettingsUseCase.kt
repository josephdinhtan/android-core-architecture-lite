package com.jddev.androidcorearchlite.domain.usecase

import com.jddev.androidcorearchlite.domain.model.ThemeMode
import com.jddev.androidcorearchlite.domain.repository.SettingsRepository
import javax.inject.Inject

class GetThemeSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) : BaseUseCase<Unit, ThemeMode> {
    override suspend fun execute(params: Unit): ThemeMode {
        return settingsRepository.getThemeMode()
    }
}