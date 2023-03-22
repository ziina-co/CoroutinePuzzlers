package `2_Cancellation`

import kotlinx.coroutines.*

fun main() = runBlocking {
    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {
        val child = launch {
            try {
                println("Child is running")
                delay(Long.MAX_VALUE)
            } finally {
                println("Child is cancelled")
            }
        }

        delay(1000)
        println("Cancelling the supervisor")
        supervisor.cancel()
    }

    println("Parent is not cancelled")
}
