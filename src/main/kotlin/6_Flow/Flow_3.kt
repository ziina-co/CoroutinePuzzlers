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

/**
 * Flow 3
 */
fun itemFlow(): Flow<Item> = flow {
    (1..5).forEach { id ->
        emit(Item(id))
        delay(1000)
    }
}

fun main() = runBlocking {
    val time = now()
    val result = mutableListOf<Item>()

    itemFlow().flowOn(Dispatchers.IO).map { item ->
        delay(1000)
        Item(item.id)
    }.flowOn(Dispatchers.Default).collect { item ->
        result.add(item)
    }

    print("Result: $result")
    print("Time taken: ${time.passed}")
}