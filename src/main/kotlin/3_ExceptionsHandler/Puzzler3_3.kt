package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

// Puzzler 3.1: Coroutine Exception Handling with a SupervisorJob
// Question: What is the output of this code snippet, and why?

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Caught $exception")
    }

    val scope = CoroutineScope(SupervisorJob())

    val job = scope.launch(exceptionHandler) {
        launch {
            println("Coroutine 1 starts")
            delay(500)
            println("Coroutine 1 throws an exception")
            throw RuntimeException("Coroutine 1 exception")
        }

        launch {
            println("Coroutine 2 starts")
            delay(1000)
            println("🍬Coroutine 2 completes successfully")
        }
    }
    job.join()
}