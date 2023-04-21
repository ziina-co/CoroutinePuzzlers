package `6_Flow`

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

fun Flow<Item>.timed(delay: Duration): Flow<Pair<Item, Duration>> = flow {
    var time: Duration = Duration.ZERO

    buffer(1, BufferOverflow.DROP_OLDEST).collect { item ->
        if (time == Duration.ZERO) {
            time = now()
        }

        emit(item to (time.passed))
        delay(delay)
    }
}

fun main(): Unit = runBlocking {
    val sharedFlow: SharedFlow<Item> = itemFlow()
        .shareIn(this, SharingStarted.Eagerly, 0)
    launch {
        sharedFlow.collect {
            println(it)
        }
    }

    launch {
        sharedFlow
            .timed(1000.milliseconds)
            .collect {
                println(it)
            }
    }
}