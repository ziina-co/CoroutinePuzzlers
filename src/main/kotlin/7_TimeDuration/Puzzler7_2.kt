package `7_TimeDuration`

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import java.lang.Thread.sleep

fun main(): Unit = runBlocking {
    val time = now()

    repeat(10) {
        launch {
            sleep(100)
            print("$it: ${time.passed}")
        }
    }
}