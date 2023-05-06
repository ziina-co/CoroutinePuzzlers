package `9_Race9_2c`

import kotlinx.coroutines.*
import utils.models.Counter
import utils.now
import utils.passed
import utils.threadsScheduler
import kotlin.time.Duration.Companion.seconds

/**
 * Race 5
 */
val counter = Counter()

fun increment() {
    counter.count++
}

fun main() = runBlocking {
    val time = now()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 2.threadsScheduler

    repeat(1_000) {
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                synchronized(counter) {
                    increment()
                }
            }
        }
    }

    withTimeout(10.seconds) {
        joinAll(*jobs.toTypedArray())
    }

    print("Final count: ${counter.pretty} in ${time.passed}")
}
/* options
1) 1_000_000
2) 100_000..999_999
3) < 100_000
4) crash
 */
