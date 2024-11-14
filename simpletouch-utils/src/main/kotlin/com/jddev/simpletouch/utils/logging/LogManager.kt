package com.jddev.simpletouch.utils.logging

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LogManager @Inject constructor(appTree: AppTree, coroutineScope: CoroutineScope) {
    private val _logcatListView = MutableStateFlow<List<LogModel>>(emptyList())
    val logcatListView = _logcatListView.asStateFlow()

    private val logcatList = mutableListOf<LogModel>()

    init {
        coroutineScope.launch {
            appTree.logMessage.collect {
                logcatList.add(it)
                _logcatListView.tryEmit(logcatList)
            }
        }
    }

    fun clear() {
        logcatList.clear()
        _logcatListView.tryEmit(logcatList)
    }
}