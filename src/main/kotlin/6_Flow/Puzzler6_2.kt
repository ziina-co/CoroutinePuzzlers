package `6_Flow`

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

// Puzzler 6.2: Flow Combinators
fun main() = runBlocking {
    val nums = (1..3).asFlow()
    val strs = flowOf("one", "two", "three")

    nums.zip(strs) { a, b -> "$a -> $b" }
        .collect { println(it) }
}
