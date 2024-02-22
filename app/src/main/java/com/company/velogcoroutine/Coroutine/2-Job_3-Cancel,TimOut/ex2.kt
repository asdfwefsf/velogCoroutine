package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Coroutine은 상호 협력한다.
// delay가 있을 때마다 다른 Coroutine에게 Thread를 양보한다.
// Coroutine은 가벼워서 10만개까지 쌉가능이야.
suspend fun doOneTwoThree2() = coroutineScope {
    val job = launch {
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }
    job.join()
    launch {
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }

    repeat(100_000) {
        // repeat()을 통해서 launch{}를 이용한 CoroutineScope가 100,000만개 만드는데
        // delay(500L)이 있으므로 , 500ms 동안 println("launch3: ${Thread.currentThread().name}")이
        // 100_000만회 실행되고 500ms가 지나면 println("2!")가 100_000만회 실행된다.
        launch {
            println("launch3: ${Thread.currentThread().name}")
            delay(500L)
            println("2!")
        }
    }
    println("4!")
}
fun main() = runBlocking{
    doOneTwoThree2()
}