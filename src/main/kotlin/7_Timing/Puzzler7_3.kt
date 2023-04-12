package `7_Timing`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {
    val startTime = System.currentTimeMillis()

    launch {
        repeat(10) {
            delay(100)
            println("$it: ${System.currentTimeMillis() - startTime}")
        }
    }
}