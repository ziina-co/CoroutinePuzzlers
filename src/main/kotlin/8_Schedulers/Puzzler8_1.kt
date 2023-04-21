@file:OptIn(DelicateCoroutinesApi::class, DelicateCoroutinesApi::class)

package `8_Schedulers`

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import utils.threadsScheduler

private suspend fun heavyComputation(taskId: Int): Int {
    println("Task $taskId started")
    delay(1000L)
    println("Task $taskId completed")
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

    println("Result: ${task1.await() + task2.await() + task3.await()}")

    println("Total time: ${time.passed}")
}