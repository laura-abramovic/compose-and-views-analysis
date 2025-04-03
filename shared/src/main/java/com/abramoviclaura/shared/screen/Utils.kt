package com.abramoviclaura.shared.screen

import android.util.Log
import kotlin.system.measureNanoTime
import kotlin.time.Duration.Companion.nanoseconds

object LogTag {
    const val NAVIGATION_TIME = "navigation time"
    const val LIST_DETAILS_NAVIGATION_TIME = "list details navigation time"
    const val BUTTON_CLICK_TIME = "button click time"
    const val INPUT_TIME = "input time"
}

fun logMillis(tag: String, block: () -> Unit) {
    val time = measureNanoTime { block() }
    val duration = time.nanoseconds.inWholeMicroseconds.toFloat() / 1000

    Log.d("Response time: $tag (ms)", "$duration")
}
