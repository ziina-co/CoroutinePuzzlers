package `8_Schedulers`

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

private suspend fun heavyComputation(taskId: Int): Int {
    println("Task $taskId started")
    delay(1000L)
    println("Task $taskId completed")
    return taskId
}

fun main() = runBlocking {
    val customDispatcher = newFixedThreadPoolContext(
        nThreads = 2,
        name = "CustomDispatcher"
    )

    val time = measureTimeMillis {
        val task1 = async(customDispatcher) {
            heavyComputation(1)
        }

        val task2 = async(customDispatcher) {
            heavyComputation(2)
        }

        val task3 = async(customDispatcher) {
            heavyComputation(3)
        }

        println("Result: ${task1.await() + task2.await() + task3.await()}")
    }

    println("Total time: $time ms")
}