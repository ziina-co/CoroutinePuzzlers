package `1_Scope1_2`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import utils.threadName
import utils.threadsScheduler

/**
 * Scope 3
 */
fun main(): Unit = runBlocking {
    val context1 = 1.threadsScheduler("1")
    val context2 = 1.threadsScheduler("2")

    launch(context1) {
        print("A$threadName ")

        withContext(context2) {
            delay(500)
            print("B$threadName ")
        }

        print("C$threadName ")
    }
}

/*
gitGraph
    commit id: "start"
    commit id: "launch(context1)"
    branch context1
    commit id: "print A1" tag: "A1" type:HIGHLIGHT
    commit id: "withContext(context2)"
    branch context2
    commit id: "delay 500ms"
    commit id: "print B2" tag: "B2" type:HIGHLIGHT
    checkout context1
    merge context2
    commit id: "print C1" tag: "C1" type:HIGHLIGHT
    checkout main
    commit id: "end"
 */