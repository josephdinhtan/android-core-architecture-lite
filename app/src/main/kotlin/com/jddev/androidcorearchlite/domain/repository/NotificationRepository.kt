package com.jddev.androidcorearchlite.domain.repository

interface NotificationRepository {
    // Intelligence charging notification
    fun showChargeLimitNotification()
    fun cancelChargeLimitNotification()

    // Notification UI
    fun showSimpleNotification(title: String? = null)
    fun updateSimpleNotification()
    fun cancelSimpleNotification()
}