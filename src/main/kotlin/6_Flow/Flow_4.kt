package `4_Flow`

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import utils.now
import utils.passed
/**
 * Flow 4
 */
private fun stringFlow(): Flow<String> = flow {
    ('A'..'E').forEach { char ->
        emit("$char->")
        delay(50)
    }
}

@OptIn(FlowPreview::class)
fun main() = runBlocking {
    val time = now()
    var result = ""
    stringFlow().flatMapMerge { value ->
        flow {
            withContext(Dispatchers.IO) {
                delay(100)
                emit(value)
            }
        }
    }.collect { item ->
        result+=item
    }

    print(result + " ${time.passed}")
}