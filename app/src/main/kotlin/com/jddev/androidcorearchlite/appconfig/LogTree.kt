package com.jddev.androidcorearchlite.appconfig

import timber.log.Timber

class AppTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        val className = super.createStackElementTag(element)?.split("$")?.get(0)
        return "CoreArchLite($className.kt:${element.lineNumber})#${element.methodName}"
    }
}