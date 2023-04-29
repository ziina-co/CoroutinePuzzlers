package `9_Race9_2`

import kotlinx.coroutines.*
import kotlin.random.Random.Default.nextLong

data class Counter(var count: Int = 0)

val counter = Counter()

suspend fun increment() {
    val oldValue = counter.count
    val newValue = oldValue + 1
    delay(nextLong(0, 2))
    counter.count = newValue
}

fun main() = runBlocking {
    val jobs = mutableListOf<Job>()
    val customDispatcher = newFixedThreadPoolContext(
        nThreads = 2,
        name = "CustomDispatcher"
    )

    repeat(1_000) {
        synchronized(this) {
            jobs += launch(customDispatcher) {
                repeat(1_000) {
                    increment()
                }
            }
        }
    }

    joinAll(*jobs.toTypedArray())

    println("Final count: ${
        counter.count.toString()
            .reversed()
            .chunked(3)
            .map { it.reversed() }
            .reduceRight { s, acc -> "${acc}_${s}" }
    }"
    )
}
