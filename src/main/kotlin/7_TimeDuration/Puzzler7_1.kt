package `7_TimeDuration`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import java.lang.Thread.sleep


fun main(): Unit = runBlocking {
    val time = now()

    launch {
        println("A: ${time.passed}")
        delay(1000L)
        println("B: ${time.passed}")
    }

    launch {
        println("C: ${time.passed}")
        sleep(1000L)
        println("D: ${time.passed}")
    }

    launch {
        println("E: ${time.passed}")
        delay(2000L)
        println("F: ${time.passed}")
    }
}