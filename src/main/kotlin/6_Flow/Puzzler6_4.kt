package `6_Flow`

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.runBlocking

fun number(): Flow<Int> = flow {
    emit(1)
    emit(2)
    emit(3)
    throw RuntimeException("Failed to emit number")
}

fun main() = runBlocking {
    var retryCount = 0
    val result = mutableListOf<Int>()
    number().retryWhen { _, _ ->
        if (retryCount < 3) {
            retryCount++
            true
        } else {
            false
        }
    }.catch { t -> println("Caught exception: $t") }.collect {
        result.add(it)
    }

    println("Result: $result")
    println("Retry count: $retryCount")
}