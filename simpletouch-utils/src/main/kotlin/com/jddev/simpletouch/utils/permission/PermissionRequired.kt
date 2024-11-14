package com.jddev.simpletouch.utils.permission

import android.Manifest

internal fun getPermissionRequired(androidVersion: Int) : List<String> {
    return when(androidVersion) {
        android.os.Build.VERSION_CODES.R -> android11Permissions
        android.os.Build.VERSION_CODES.S -> android12Permissions
        else -> android13Permissions
    }
}

private val android11Permissions by lazy {
    listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_PHONE_STATE,
    )
}

private val android12Permissions by lazy {
    listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_PHONE_STATE,
    )
}

private val android13Permissions by lazy {
    listOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_PHONE_STATE,
    )
}