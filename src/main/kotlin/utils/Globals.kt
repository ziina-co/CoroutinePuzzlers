package utils

import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

val threadName: String?
    get() = Thread
        .currentThread()
        .name

fun now(): Duration = System
    .currentTimeMillis()
    .milliseconds