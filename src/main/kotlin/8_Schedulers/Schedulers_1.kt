package `8_Schedulers`

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

    println("Result: ${task1.await() + task2.await() + task3.await()}")

    println("Total time: ${time.passed}")
}