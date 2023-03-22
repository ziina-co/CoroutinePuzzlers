package `4_Timeout`

import kotlinx.coroutines.*

// Puzzler 4.1: Coroutine Timeouts
// Question: What is the output of this code snippet, and why?

// не слишком ли просто? 👍
fun main() = runBlocking {
    withTimeoutOrNull(1300L) {
        repeat(5) { i ->
            println("Coroutine: I'm working on step $i")
            delay(500)
        }
    }
    println("Main: Coroutine is done")
}