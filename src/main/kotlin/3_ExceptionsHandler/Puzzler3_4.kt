package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

// Puzzler 3.1: Coroutine Exception Handling with a SupervisorJob
// Question: What is the output of this code snippet, and why?

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        print("Caught $exception")
    }

    val scope = CoroutineScope(Job())

    val job = scope.launch(exceptionHandler + SupervisorJob()) {
        launch {
            print("Coroutine 1 starts")
            delay(500)
            print("Coroutine 1 throws an exception")
            throw RuntimeException("Coroutine 1 exception")
        }

        launch {
            print("Coroutine 2 starts")
            delay(1000)
            print("🍬Coroutine 2 completes successfully")
        }
    }
    job.join()
}