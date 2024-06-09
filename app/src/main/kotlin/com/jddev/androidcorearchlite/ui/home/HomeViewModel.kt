package com.jddev.androidcorearchlite.ui.home

import androidx.lifecycle.ViewModel

class HomeViewModel() : ViewModel() {


    companion object {
        const val TAG = "HomeViewmodel"

        fun provideFactory() = object : androidx.lifecycle.ViewModelProvider.Factory {
            override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel() as T
            }
        }
    }
}