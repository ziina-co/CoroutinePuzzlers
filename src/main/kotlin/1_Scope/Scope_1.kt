package `1_Scope1_1`

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Scope 1
 */
fun main(): Unit = runBlocking {
    val JobA = launch {
        delay(500)
        print("A")
    }

    val JobB = launch {
        delay(1000)
        print("B")
    }

    print("C")
}


/*
gitGraph
    commit id: "start"
    commit id: "launch JobA"
    branch JobA
    commit id: "delay 500ms"
    checkout main
    commit id: "launch JobB"
    branch JobB
    commit id: "delay 1000ms"
    checkout main
    commit id: "print C" tag: "C" type:HIGHLIGHT
    checkout JobA
    commit id: "print A" tag: "A" type:HIGHLIGHT
    checkout JobB
    commit id: "print B" tag: "B" type:HIGHLIGHT
    checkout main
    commit id: "end"
 */