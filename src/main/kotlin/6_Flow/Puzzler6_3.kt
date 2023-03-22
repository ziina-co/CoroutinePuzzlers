package `6_Flow`

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Puzzler 6.3: Flow Exception Handling
suspend fun performRequest(request: Int): String {
    delay(100)
    if (request == 2) throw RuntimeException("Error on request $request")
    return "Result for request $request"
}

fun requestFlow() = flow {
    for (i in 1..3) {
        emit(i)
    }
}

fun main() = runBlocking {
    requestFlow()
        .map { request -> performRequest(request) }
        .catch { e -> emit("Caught error: ${e.localizedMessage}") }
        .collect { response -> println(response) }
}
