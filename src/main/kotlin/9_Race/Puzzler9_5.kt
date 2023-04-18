package `9_Race9_5`

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex

data class Counter(var count: Int = 0)

val counter = Counter()
val mutex = Mutex()

fun increment() {
    counter.count++
}

fun main() = runBlocking {
    val jobs = mutableListOf<Job>()
    val customDispatcher = newFixedThreadPoolContext(
        nThreads = 100,
        name = "CustomDispatcher"
    )

    val counterJob = launch {
        while (isActive) {
            delay(1)
            print("Counter: ${counter.count}\r")
        }
    }

    repeat(1_000) {
        mutex.lock()
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                increment()
            }
        }
        mutex.unlock()
    }

    joinAll(*jobs.toTypedArray())
    counterJob.cancel()

    println("Final count: ${
        counter.count.toString()
            .reversed()
            .chunked(3)
            .map { it.reversed() }
            .reduceRight { s, acc -> "${acc}_${s}" }
    }"
    )
}
