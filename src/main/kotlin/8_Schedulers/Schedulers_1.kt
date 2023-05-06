@file:OptIn(DelicateCoroutinesApi::class, DelicateCoroutinesApi::class)

package `8_Schedulers`

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import utils.threadsScheduler

/**
 * Schedulers 1
 */
private suspend fun heavyComputation(taskId: Int): Int {
    print("Task $taskId started")
    delay(1000L)
    print("Task $taskId completed")
    return taskId
}

fun main() = runBlocking {
    val customDispatcher = 2.threadsScheduler

    val time = now()

    val task1 = async(customDispatcher) {
        heavyComputation(taskId = 1)
    }

    val task2 = async(customDispatcher) {
        heavyComputation(taskId = 2)
    }

    val task3 = async(customDispatcher) {
        heavyComputation(taskId = 3)
    }

    print("Result: ${task1.await() + task2.await() + task3.await()}")

    print("Total time: ${time.passed}")
}