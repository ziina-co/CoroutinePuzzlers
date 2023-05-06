package `9_Race9_5a`

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import utils.models.Counter
import utils.threadsScheduler
import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

/**
 * Race 10
 */
val counter = Counter()
val mutex = Mutex()

suspend fun increment() {
    delay(Random.nextLong(0, 2))
    counter.count++
}

fun main() = runBlocking {
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.threadsScheduler

    repeat(1_000) {
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                mutex.withLock {
                    increment()
                }
            }
        }
    }

    withTimeout(10.seconds) {
        joinAll(*jobs.toTypedArray())
    }
    print("Final count: ${counter.pretty}")

}

/* options
1) 1_000_000
2) 100_000..999_999
3) < 100_000
4) crash
 */
