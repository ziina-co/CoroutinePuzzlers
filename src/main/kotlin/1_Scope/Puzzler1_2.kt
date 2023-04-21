package `1_Scope1_2`

import kotlinx.coroutines.*
import utils.threadName


// Puzzler 1.2: Coroutine Context Switching
// Question: What is the output of this code snippet, and why?
@OptIn(DelicateCoroutinesApi::class)
fun main(): Unit = runBlocking {
    val context1 = newSingleThreadContext("Context1")
    val context2 = newSingleThreadContext("Context2")

    launch(context1) {
        println("Starting in context1: $threadName")

        withContext(context2) {
            println("Switched to context2: $threadName")
        }

        println("Back to context1: $threadName")
    }
}