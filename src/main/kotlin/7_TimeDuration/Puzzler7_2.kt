package `7_TimeDuration`

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import java.lang.Thread.sleep

fun main() {
    val time = now()

    runBlocking {
        repeat(10) {
            launch {
                sleep(100)
                print("$it: ${time.passed}, ")
            }
        }
    }

    println("\nTotal: ${time.passed}")
}

/*
Total time?
a) ~1s
b) Crash
c) ~100ms
d) ♾️
 */