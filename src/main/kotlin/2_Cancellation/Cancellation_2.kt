package `2_Cancellation`

import kotlinx.coroutines.*

/**
 * Cancellation 2
 */
fun main() = runBlocking {
    val supervisor = SupervisorJob()

    with(CoroutineScope(coroutineContext + supervisor)) {
        val child = launch {
            try {
                print("A")
                delay(Long.MAX_VALUE)
            } finally {
                print("B")
            }
        }

        delay(1000)
        print("C")
        supervisor.cancel()
    }

    print("D")
}

/*
gitGraph
    commit id: "start"
    commit id: "launch child"
    branch child
    checkout main
    commit id: "delay 1000"
    checkout child
    commit id: "print A" tag: "A" type:HIGHLIGHT
    commit id: "delay ♾️"
    checkout main
    commit id: "print C" tag: "C" type:HIGHLIGHT
    commit id: "supervisor.cancel()"
    commit id: "print D" tag: "D" type:HIGHLIGHT
    checkout child
    commit id: "CancelExcptn💥"
    commit id: "print B" tag: "B" type:HIGHLIGHT
    checkout main
    commit id: "end"

 */