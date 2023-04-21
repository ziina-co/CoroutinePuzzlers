package `9_Race9_2b`

import kotlinx.coroutines.*

data class Counter(var count: Int = 0)

val counter = Counter()

fun increment() {
    counter.count++
}

fun main() = runBlocking {
    val jobs = mutableListOf<Job>()
    val customDispatcher = newFixedThreadPoolContext(
        nThreads = 2,
        name = "CustomDispatcher"
    )

    repeat(1_000) {
        jobs += launch(customDispatcher) {
            repeat(1_000) {
                synchronized(this) {
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
