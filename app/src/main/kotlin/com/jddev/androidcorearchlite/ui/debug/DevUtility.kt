package com.jddev.androidcorearchlite.ui.debug

import com.simpletouch.utils.di.CoroutineScopeIO
import com.simpletouch.utils.logging.LogManager
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DevUtility @Inject constructor(
    var logManager: LogManager,
    @CoroutineScopeIO var coroutineScope: CoroutineScope,
) {
    fun commandRequest() {
    }

    fun saveATestSettingsToDb() {
    }

    fun getATestSettingsFromDb() {
    }

    fun tryReportStatus() {
    }

    fun tryCaptureImageWithoutPreview() {
    }

    fun tryUploadVideoToServer() {
    }
}