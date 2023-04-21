package utils

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.newFixedThreadPoolContext

@OptIn(DelicateCoroutinesApi::class)
val Int.threadsScheduler
    get() = newFixedThreadPoolContext(
        nThreads = this,
        name = "CustomDispatcher"
    )