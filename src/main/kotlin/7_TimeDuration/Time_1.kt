package `7_TimeDuration`

import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import java.lang.Thread.sleep


/**
 * Time 1
 */
fun main(): Unit = runBlocking {
    val time = now()

    val job1 = launch {
        print("A")
        delay(1000L)
        print("B")
    }

    val job2 = launch {
        print("C")
        sleep(1000L)
        print("D")
    }

    val job3 = launch {
        print("E")
        delay(2000L)
        print("F")
    }

    joinAll(job1, job2, job3)
    print(" ${time.passed}")
}

/*
options:
a) ACDEBF 3s
b) ACEBDF 2s
c) AC Crash
d) ABCDEF 4s
 */