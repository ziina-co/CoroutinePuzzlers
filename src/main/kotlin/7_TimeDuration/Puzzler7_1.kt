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
        print("A: ${time.passed}")
        delay(1000L)
        print("B: ${time.passed}")
    }

    launch {
        print("C: ${time.passed}")
        sleep(1000L)
        print("D: ${time.passed}")
    }

    launch {
        print("E: ${time.passed}")
        delay(2000L)
        print("F: ${time.passed}")
    }
}