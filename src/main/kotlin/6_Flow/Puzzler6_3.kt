import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

data class Item(val id: Int)

fun itemFlow(): Flow<Item> = flow {
    (1..5).forEach { id ->
        emit(Item(id))
        delay(1000)
    }
}

fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val result = mutableListOf<Item>()
    itemFlow().flowOn(Dispatchers.IO).map { item ->
        delay(1000)
        Item(item.id)
    }.flowOn(Dispatchers.Default).collect { item ->
        result.add(item)
    }
    val endTime = System.currentTimeMillis()

    println("Result: $result")
    println("Time taken: ${endTime - startTime} ms")
}