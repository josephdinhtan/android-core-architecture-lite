package com.jddev.simpletouch.utils.logging

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppTree : Timber.DebugTree() {
    private val _logMessage = MutableSharedFlow<LogModel>(replay = 1)
    val logMessage = _logMessage.asSharedFlow()

    private val simpleDateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())

    override fun createStackElementTag(element: StackTraceElement): String {
        val className = super.createStackElementTag(element)?.split("$")?.get(0)
        return "Simpletouch ($className.kt:${element.lineNumber})#${element.methodName}"
    }

    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        super.log(priority, tag, message, t)
        message.let { _logMessage.tryEmit(LogModel(simpleDateFormat.format(Date()),priority, tag, message)) }
    }
}