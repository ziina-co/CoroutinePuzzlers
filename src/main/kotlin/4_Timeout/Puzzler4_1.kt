package `4_Timeout`

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main() = runBlocking {
    withTimeout(1300L) {
        repeat(5) { i ->
            print("$i")
            delay(500)
        }
    }

    print("✅")
}

/*
options:
a) 012 Crash
b) 012✅
c) 0123✅
d) 0123 Crash
 */