package utils

import kotlin.time.Duration

val Duration.passed: Duration
    get() = now() - this
