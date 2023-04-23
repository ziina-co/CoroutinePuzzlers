package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        print("💥")
    }

    val scope = CoroutineScope(SupervisorJob())
    val job = scope.launch(exceptionHandler) {
        launch {
            print("A")
            delay(500)
            print("B")
            throw Exception()
        }

        launch {
            print("C")
            delay(1000)
            print("🍬")
        }
    }
    job.join()
}