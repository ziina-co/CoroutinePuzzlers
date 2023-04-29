package `9_Race9_3_Semaphorea`

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit

data class Counter(var count: Int = 0)

val counter = Counter()
val semaphore = Semaphore(1)

suspend fun increment() {
    semaphore.withPermit {
        counter.count++
    }
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
                increment()
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
