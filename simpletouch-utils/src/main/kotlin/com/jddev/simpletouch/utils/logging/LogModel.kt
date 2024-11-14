package com.jddev.simpletouch.utils.logging

/**
 * Created by Andy Ha on 7/29/2023.
 */
data class LogModel(
  val time: String,
  val priority: Int,
  val tag: String?,
  val message: String
)