import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import utils.models.Item
import utils.now
import utils.passed


fun itemFlow(): Flow<Item> = flow {
    (1..5).forEach { id ->
        emit(Item(id))
        delay(50)
    }
}

fun main() = runBlocking {
    val time = now()
    val result = mutableListOf<Item>()

    itemFlow().flowOn(Dispatchers.IO).map { item ->
        delay(100)
        Item(item.id * 2)
    }.flowOn(Dispatchers.Default).collect { item ->
        result.add(item)
    }

    println("Result: $result")
    println("Time taken: ${time.passed}")
}