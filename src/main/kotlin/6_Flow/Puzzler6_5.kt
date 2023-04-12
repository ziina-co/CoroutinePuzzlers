package `6_Flow`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

data class Item(val id: Int)

fun itemFlow(): Flow<Item> = flow {
    (1..5).forEach { id ->
        emit(Item(id))
        delay(50)
    }
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
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
    val endTime = System.currentTimeMillis()

    println("Result: $result")
    println("Time taken: ${endTime - startTime} ms")
}