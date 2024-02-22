package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.*

// withTimeout : 일정 시간이 지난 후 Coroutine을 종료하고 싶을 때
// 이때 해당 Coroutine이 취소가 되면 , TimeoutCancellationException이 발생한다. : 너무 귀찮아 -> ex10.kt
suspend fun doCount9() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L

        while (i <= 10 && isActive) {
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }
}

fun main() = runBlocking {
    withTimeout(500L) {
        doCount9()
    }
}