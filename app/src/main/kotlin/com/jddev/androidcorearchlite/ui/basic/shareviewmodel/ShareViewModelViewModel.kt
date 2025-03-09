package com.jddev.androidcorearchlite.ui.basic.shareviewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ShareViewModelViewModel: ViewModel() {

    private val _count = MutableStateFlow<Int>(0)
    val count = _count.asStateFlow()

    fun increaseCount() {
        _count.value++
    }

    fun decreaseCount() {
        _count.value--
    }
}