import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import utils.now
import utils.passed
import kotlin.time.Duration.Companion.seconds

/**
 * Flow 3
 */
private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char->")
        delay(1.seconds)
    }
}

fun main() = runBlocking {
    val time = now()
    var result = ""

    stringFlow()
        .flowOn(Dispatchers.IO)
        .map { item ->
            delay(0.5.seconds)
            item
        }
        .flowOn(Dispatchers.Default)
        .collect { result += it }

    print("Result: $result    ${time.passed}")
}