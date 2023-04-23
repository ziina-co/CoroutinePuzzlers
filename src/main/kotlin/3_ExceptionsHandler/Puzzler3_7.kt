package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

// Puzzler 3.6: Coroutine Exception Handling with a SupervisorJob
// Question: What is the output of this code snippet, and why?

fun main(): Unit = runBlocking {
    val job = SupervisorJob()
    val scope = CoroutineScope(Job())

    val job1 = scope.launch {
        print("Coroutine 1 starts")
        delay(500)
        print("Coroutine 1 throws an exception")
        throw RuntimeException("Coroutine 1 exception")
    }

    val job2 = scope.launch(job) {
        print("Coroutine 2 starts")
        delay(1000)
        print("🍬Coroutine 2 completes successfully")
    }

    joinAll(job1, job2)
}