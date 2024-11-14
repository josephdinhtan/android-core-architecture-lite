package com.jddev.simpletouch.utils.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IoDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class MainDispatcher

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class GlobalScope

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class CoroutineScopeIO

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @IoDispatcher
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun providesMainDispatcher(): CoroutineDispatcher = Dispatchers.Main

    @Provides
    @GlobalScope
    fun provideGlobalScope(): CoroutineScope = kotlinx.coroutines.GlobalScope

    @Provides
    @CoroutineScopeIO
    fun provideCoroutineIoScope(): CoroutineScope = kotlinx.coroutines.CoroutineScope(SupervisorJob() +  Dispatchers.IO)
}