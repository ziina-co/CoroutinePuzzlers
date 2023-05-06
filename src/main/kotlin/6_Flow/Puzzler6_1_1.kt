import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

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
        .map { request ->
            try {
                performRequest(request)
            } catch (e: Exception) {
                println("Caught error in try catch: ${e.localizedMessage}")
            }
        }
        .catch { e -> emit("Caught error: ${e.localizedMessage}") }
        .collect { response -> println(response) }
}