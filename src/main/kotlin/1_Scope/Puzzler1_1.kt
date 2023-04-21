package `1_Scope1_1`

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Puzzler 1.1: Coroutine Scope Confusion
// Question: What is the order of the outputs, and why?
fun main(): Unit = runBlocking {
    launch {
        delay(500)
        println("Coroutine 1")
    }

    coroutineScope {
        launch {
            delay(1000)
            println("Coroutine 2")
        }
    }

    println("End of main")
}