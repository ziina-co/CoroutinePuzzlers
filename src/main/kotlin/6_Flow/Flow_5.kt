package `6_Flow`

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import utils.now
import utils.passed

/**
 * Flow 5
 */
data class Item(val id: Int)

fun itemFlow(): Flow<Item> = flow {
    (1..5).forEach { id ->
        emit(Item(id))
        delay(50)
    }
}

@OptIn(FlowPreview::class)
fun main() = runBlocking {
    val time = now()
    val result = mutableListOf<Item>()
    itemFlow().flatMapMerge { item ->
        flow {
            withContext(Dispatchers.IO) {
                delay(100)
                emit(Item(item.id * 2))
            }
        }
    }.collect { item ->
        result.add(item)
    }

    print("Result: $result")
    print("Time taken: ${time.passed}")
}