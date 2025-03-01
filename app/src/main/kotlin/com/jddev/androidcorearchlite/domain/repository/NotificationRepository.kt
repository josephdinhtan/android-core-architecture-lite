package com.jddev.androidcorearchlite.domain.repository

interface NotificationRepository {
    fun showChargeLimitNotification()
    fun cancelChargeLimitNotification()
}