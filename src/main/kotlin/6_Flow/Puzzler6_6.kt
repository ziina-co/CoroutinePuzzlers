package `6_Flow`

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun Flow<Item>.timed(delay: Long): Flow<Pair<Item, Long>> = flow {
    var startTime = 0L

    buffer(1, BufferOverflow.DROP_OLDEST).collect { item ->
        if (startTime == 0L) {
            startTime = System.currentTimeMillis()
        }

        emit(item to (System.currentTimeMillis() - startTime))
        delay(delay)
    }
}

fun main(): Unit = runBlocking {
    val sharedFlow = itemFlow()
        .shareIn(this, SharingStarted.Eagerly, 0)
    launch {
        sharedFlow.collect {
            println(it)
        }
    }

    launch {
        sharedFlow
            .timed(1000)
            .collect {
                println(it)
            }
    }
}