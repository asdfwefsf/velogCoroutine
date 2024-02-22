package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.*

// job.cancel : 현재 실행중인 Coroutine을 취소 시킨다. 하지만 모든 job이 취소가 가능한 것은 아니다 : ex4.kt 보기.
// 여기서 job이 나타내는 것은 launch{}라는 CoroutineBuilder의 반환값이야.

suspend fun doOneTwoThree3() = coroutineScope{
    val job1 = launch {
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }
    val job2 = launch {
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }
    val job3 = launch {
        println("launch3: ${Thread.currentThread().name}")
        delay(500L)
        println("2!")
    }
    delay(800L)
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}

fun main() = runBlocking {
    doOneTwoThree3()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}