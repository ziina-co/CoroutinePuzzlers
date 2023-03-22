package `5_AsyncAwait`

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

// Puzzler 5.1: Async and Await
// Question: What is the output of this code snippet, and why?
suspend fun performTask(id: Int): Int {
    delay(1000)
    return id * 2
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()

    val deferred1 = async { performTask(1) }
    val deferred2 = async { performTask(2) }

    println("Waiting for results")
    val result1 = deferred1.await()
    val result2 = deferred2.await()

    println(
        "Results: $result1, $result2.\n" +
                "⌛️: ${(System.currentTimeMillis() - startTime).milliseconds}"
    )
}