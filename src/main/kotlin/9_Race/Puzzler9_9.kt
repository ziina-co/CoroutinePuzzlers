@file:OptIn(DelicateCoroutinesApi::class)

package `9_Race9_5`

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import utils.models.Counter
import utils.now
import utils.passed
import utils.threadsScheduler
import kotlin.time.Duration.Companion.seconds

val counter = Counter()
val mutex = Mutex()

fun increment() {
    counter.count++
}

fun main() = runBlocking {
    val time = now()
    val jobs = mutableListOf<Job>()
    val customDispatcher = 100.threadsScheduler

    repeat(1_000) {
        mutex.lock()
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                increment()
            }
        }
        mutex.unlock()
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
