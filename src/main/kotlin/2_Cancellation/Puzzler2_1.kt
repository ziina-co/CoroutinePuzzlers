package `2_Cancellation`

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Puzzler 2.1: Coroutine Cancellation
// Question: What will be the output of this code snippet, and why?
fun main() = runBlocking {
    val job = launch {
        try {
            repeat(5) { i ->
                println("Coroutine: I'm working on step $i")
                delay(1000)
            }
        } finally {
            println("Coroutine: I'm being cancelled")
        }
    }

    delay(2500)
    println("Main: I'm tired of waiting")
    job.cancelAndJoin()
    println("Main: Coroutine is cancelled")
}