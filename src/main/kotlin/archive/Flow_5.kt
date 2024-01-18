package `5_Flow`

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
/**
 * Flow 5
 */
private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char->")
        delay(50)
    }
}
fun Flow<String>.timed(delay: Duration): Flow<String> = flow {
    var time: Duration = Duration.ZERO
    buffer(1, BufferOverflow.DROP_OLDEST).collect { item ->
        if (time == Duration.ZERO) {
            time = now()
        }
        emit("${time.passed} $item")
        delay(delay)
    }
}

fun main(): Unit = runBlocking {
    val sharedFlow = stringFlow().shareIn(this, SharingStarted.Lazily, 0)
    launch {
        sharedFlow.collect {
            print(it)
        }
    }
    launch {
        sharedFlow
            .timed(1000.milliseconds)
            .collect {
                print(it)
            }
    }
}