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
private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char->")
        delay(1000)
    }
}

fun main() = runBlocking {
    val time = now()
    var result = ""

    stringFlow().flowOn(Dispatchers.IO).map { item ->
        delay(500)
        item
    }.flowOn(Dispatchers.Default).collect { item ->
        result += item
    }

    print("Result: $result    ${time.passed}")
}