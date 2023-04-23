package `2_Cancellation`

import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val job = launch {
        try {
            repeat(5) { i ->
                print("A$i")
                delay(100)
            }
        } finally {
            print("B")
        }
    }

    delay(250)
    print("C")
    job.cancelAndJoin()
    print("D")
}


/*
gitGraph
    commit id: "start"
    commit id: "launch Job"
    branch LoopJob
    checkout main
    checkout LoopJob
    commit id: "print A0" tag: "A0" type:HIGHLIGHT
    commit id: "print A1" tag: "A1" type:HIGHLIGHT
    commit id: "print A2" tag: "A2" type:HIGHLIGHT
    checkout main
    commit id: "print C" tag: "C" type:HIGHLIGHT
    commit id: "job.cancelAndJoin"
    checkout LoopJob
    merge main id: "CanceltnExcptn💥"
    commit id: "print B" tag: "B" type:HIGHLIGHT
    checkout LoopJob
    checkout main
    merge LoopJob
    commit id: "print D" tag: "D" type:HIGHLIGHT
    commit id: "end"

 */