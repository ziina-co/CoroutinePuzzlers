package `7_Timing`

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Thread.sleep

fun main(): Unit = runBlocking {
    val startTime = System.currentTimeMillis()

    repeat(10) {
        launch {
            sleep(100)
            println("$it: ${System.currentTimeMillis() - startTime}")
        }
    }
}