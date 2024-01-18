package `2_Flow`

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.random.Random

/**
 * Flow 2
 */
fun numberFlow(): Flow<Int> = flow {
    repeat(3) {
        delay(100)
        emit(Random.nextInt(10))
    }
}

fun main(): Unit = runBlocking {
    withTimeoutOrNull(250) {
        numberFlow().collect {
            delay(50)
            print("$it ")
        }
    }
}