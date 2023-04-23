package `3_ExceptionsHandler3_1`

import kotlinx.coroutines.*

@OptIn(DelicateCoroutinesApi::class)
fun main() = runBlocking {
    val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        print("💥")
    }

    val job = GlobalScope.launch(exceptionHandler) {
        launch {
            print("A")
            delay(500)
            print("B")
            throw RuntimeException()
        }

        launch {
            print("C")
            delay(1000)
            print("🍬")
        }
    }

    job.join()
}


/*
gitGraph
    commit id: "start"
    commit id: "launch job"
    branch Job
    commit id: "launch child1"
    branch Child1
    commit id: "launch child2"
    branch Child2
    checkout Child1
    commit id: "print A" tag: "A" type:HIGHLIGHT
    commit id: "delay 500"
    checkout Child2
    commit id: "print D" tag: "D" type:HIGHLIGHT
    commit id: "delay 1000"
    checkout Child1
    commit id: "print B" tag: "B" type:HIGHLIGHT
    checkout Job
    merge Child1 id: "Excptn💥"
    commit id: "handle exception" tag: "💥" type:HIGHLIGHT
    checkout Child2
    merge Job id: "Cancel"
    checkout main
    merge Job id: "job.join()"
    commit id: "end"
 */