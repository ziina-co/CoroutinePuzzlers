package `7_Timing`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main(): Unit = runBlocking {
    val startTime = System.currentTimeMillis()
    launch {
        println("A: ${System.currentTimeMillis() - startTime}")
        delay(1000L)
        println("B: ${System.currentTimeMillis() - startTime}")
    }

    launch {
        println("C: ${System.currentTimeMillis() - startTime}")
        sleep(1000L)
        println("D: ${System.currentTimeMillis() - startTime}")
    }

    launch {
        println("E: ${System.currentTimeMillis() - startTime}")
        delay(2000L)
        println("F: ${System.currentTimeMillis() - startTime}")
    }
}