package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

fun main(): Unit = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ -> print("💥") }
    val scope = CoroutineScope(SupervisorJob() + exceptionHandler)

    val job1 = scope.launch {
        print("A")
        delay(500)
        print("B")
        throw Exception()
    }

    val job2 = scope.launch {
        print("C")
        delay(1000)
        print("🍬")
    }

    joinAll(job1, job2)
}