package `7_TimeDuration`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed

/**
 * Time 3
 */
fun main() {
    val time = now()

    runBlocking {
        launch {
            repeat(10) {
                delay(100)
                print("$it: ${time.passed} 👉")
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