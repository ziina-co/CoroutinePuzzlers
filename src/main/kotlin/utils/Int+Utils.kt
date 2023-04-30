package utils

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.newFixedThreadPoolContext

val Int.threadsScheduler
    get() = this.threadsScheduler("${this}Threads")

@OptIn(DelicateCoroutinesApi::class)
fun Int.threadsScheduler(name: String) = newFixedThreadPoolContext(
    nThreads = this,
    name = name,
)