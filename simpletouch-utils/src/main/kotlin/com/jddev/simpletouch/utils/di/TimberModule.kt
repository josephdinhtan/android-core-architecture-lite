package com.jddev.simpletouch.utils.di

import com.jddev.simpletouch.utils.logging.AppTree
import com.jddev.simpletouch.utils.logging.LogManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class TimberModule {

    @Singleton
    @Provides
    fun provideAppTree(): AppTree {
        val appTree = AppTree()
        Timber.plant(appTree)
        return appTree
    }

    @Singleton
    @Provides
    fun provideLogManager(
        appTree: AppTree,
        @CoroutineScopeIO coroutineScope: CoroutineScope
    ): LogManager {
        return LogManager(appTree, coroutineScope)
    }
}