package `6_Flow`

// Puzzler 6.1: Flow Collection
// Question: What is the output of this code snippet, and why?

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.time.Duration.Companion.milliseconds

suspend fun numberFlow(): Flow<Int> = flow {
    for (i in 1..3) {
        delay(500)
        emit(i)
    }
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    numberFlow().collect { value ->
        println("Collected $value at ${(System.currentTimeMillis() - startTime).milliseconds}")
    }
}