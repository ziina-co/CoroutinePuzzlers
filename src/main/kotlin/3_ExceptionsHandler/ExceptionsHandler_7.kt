package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

/**
 * Exceptions 7
 */
fun main(): Unit = runBlocking {
    val scope = CoroutineScope(Job())

    val job1 = scope.launch {
        print("A")
        delay(500)
        print("B")
        throw Exception()
    }

    val job2 = scope.launch(Job()) {
        print("C")
        delay(1000)
        print("🍬")
    }

    joinAll(job1, job2)

    print("D")
}