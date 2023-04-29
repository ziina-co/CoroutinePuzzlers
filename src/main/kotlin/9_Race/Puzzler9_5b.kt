package `9_Race9_5b`

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlin.random.Random

data class Counter(var count: Int = 0)

val counter = Counter()
val mutex = Mutex()

suspend fun increment() {
    delay(Random.nextLong(0, 2))
    counter.count++
}

fun main() = runBlocking {
    val jobs = mutableListOf<Job>()
    val customDispatcher = newFixedThreadPoolContext(
        nThreads = 2,
        name = "CustomDispatcher"
    )

    val counterJob = launch {
        while (isActive) {
            delay(1)
            print("Counter: ${counter.count}\r")
        }
    }

    repeat(1_000) {
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                mutex.lock()
                increment()
                mutex.unlock()
            }
        }
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