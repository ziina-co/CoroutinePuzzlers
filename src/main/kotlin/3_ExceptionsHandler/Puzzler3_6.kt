package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

// Puzzler 3.6: Coroutine Exception Handling with a SupervisorJob
// Question: What is the output of this code snippet, and why?

fun main(): Unit = runBlocking {
    val scope = CoroutineScope(SupervisorJob())

    val job1 = scope.launch {
        println("Coroutine 1 starts")
        delay(500)
        println("Coroutine 1 throws an exception")
        throw RuntimeException("Coroutine 1 exception")
    }

    val job2 = scope.launch {
        println("Coroutine 2 starts")
        delay(1000)
        println("🍬Coroutine 2 completes successfully")
    }

    joinAll(job1, job2)
}