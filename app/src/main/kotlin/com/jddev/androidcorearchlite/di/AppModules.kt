package com.jddev.androidcorearchlite.di

import com.jddev.androidcorearchlite.ui.settings.AppSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModules {

    @Provides
    @Singleton
    fun provideAppSettings(): AppSettings {
        return AppSettings.Default
    }
}