package com.jddev.androidcorearchlite.data.di

import android.content.Context
import com.jddev.androidcorearchlite.data.repository.NotificationRepositoryImpl
import com.jddev.androidcorearchlite.data.repository.SettingsRepositoryImpl
import com.jddev.androidcorearchlite.domain.repository.NotificationRepository
import com.jddev.androidcorearchlite.domain.repository.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun bindSettingsRepository(
    ): SettingsRepository = SettingsRepositoryImpl()

    @Singleton
    @Provides
    fun bindNotificationRepository(
        @ApplicationContext context: Context,
    ): NotificationRepository = NotificationRepositoryImpl(context)
}