@file:OptIn(DelicateCoroutinesApi::class)

package `9_Race9_2`

import kotlinx.coroutines.*
import utils.models.Counter
import utils.now
import utils.passed
import utils.threadsScheduler
import kotlin.random.Random.Default.nextLong
import kotlin.time.Duration.Companion.seconds

val counter = Counter()

suspend fun increment() {
    val oldValue = counter.count
    val newValue = oldValue + 1
    delay(nextLong(0, 2))
    counter.count = newValue
}

fun main() = runBlocking {
    val time = now()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.threadsScheduler

    repeat(1_000) {
        synchronized(this) {
            jobs += launch(customDispatcher) {
                repeat(1_000) {
                    increment()
                }
            }
        }
    }

    withTimeout(10.seconds) {
        joinAll(*jobs.toTypedArray())
    }

    println("Final count: ${counter.pretty} in ${time.passed}")
}