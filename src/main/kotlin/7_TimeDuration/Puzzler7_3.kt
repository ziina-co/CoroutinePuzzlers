package `7_TimeDuration`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed

fun main(): Unit = runBlocking {
    val time = now()

    launch {
        repeat(10) {
            delay(100)
            println("$it: ${time.passed}")
        }
    }
}