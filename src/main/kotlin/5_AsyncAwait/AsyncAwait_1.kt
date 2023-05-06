package `5_AsyncAwait`

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed

/**
 * Async/Await 1
 */
suspend fun performTask(id: Int): Int {
    delay(1000)
    return id * 2
}

fun main() = runBlocking {
    val time = now()

    val deferred1 = async { performTask(1) }
    val deferred2 = async { performTask(2) }

    print("A ")
    val result1 = deferred1.await()
    val result2 = deferred2.await()

    print("$result1 $result2 ${(time.passed)}")
}

/*
options:
 a) A 2 4 1s
 b) A 1 2 2s
 c) A 1 2 1s
 d) A 2 4 2s
 */

/*
gitGraph
    commit id: "start"
    commit id: "async deferred1"
    branch Deferred1
    checkout main
    commit id: "async deferred2"
    branch Deferred2
    checkout Deferred1
    commit id: "performTask(1)"
    checkout Deferred2
    commit id: "performTask(2)"
    checkout main
    merge Deferred1 id: "deferred1.await()"
    commit id: "deferred2.await()"
    commit id: "print results and time" tag: "Results & Time" type:HIGHLIGHT
    commit id: "end"
 */
