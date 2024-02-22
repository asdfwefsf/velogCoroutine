package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// cancelAndJoin() : cancel()을 실행하고 join()을 실행하는 일은 자주 일어나는 일이기 때문에 한번에 하는
// cancelAndJoin()이 준비되어 있다.

suspend fun doCount5() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L
        while ( i <= 10) {
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }
    delay(300L)
    println("doCount Done")
}

fun main() = runBlocking {
    doCount5()
}

