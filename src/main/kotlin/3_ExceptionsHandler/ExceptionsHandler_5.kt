package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

/**
 * Exceptions 5
 */
fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ -> print("💥") }
    val scope = CoroutineScope(Job())

    val job = scope.launch(exceptionHandler) {
        supervisorScope {
            val child1 = launch {
                print("A")
                delay(500)
                print("B")
                throw Exception()
            }

            val child2 = launch {
                print("C")
                delay(1000)
                print("🍬")
            }

            joinAll(child1, child2)
        }
    }

    job.join()
}