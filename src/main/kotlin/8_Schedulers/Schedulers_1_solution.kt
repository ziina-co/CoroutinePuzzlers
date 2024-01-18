@file:OptIn(DelicateCoroutinesApi::class)

package `8_Schedulers`

import kotlinx.coroutines.*
import utils.now
import utils.passed
import utils.threadsScheduler
import java.lang.Thread.sleep

private fun heavyComputation(taskId: Int): Int {
    print("Task $taskId started")
    sleep(1000L)
    print("Task $taskId completed")
    return taskId
}

fun main() = runBlocking {
    val customDispatcher = 1.threadsScheduler
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

    println("\nResult: ${task1.await() + task2.await() + task3.await()}")

    println("Total time: ${time.passed}")
}