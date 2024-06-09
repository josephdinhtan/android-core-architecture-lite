package com.jddev.androidcorearchlite.ui.settings

import androidx.lifecycle.ViewModel

class SettingsViewModel() : ViewModel() {


    companion object {
        const val TAG = "HomeViewmodel"

        fun provideFactory() = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return SettingsViewModel() as T
            }
        }
    }
}