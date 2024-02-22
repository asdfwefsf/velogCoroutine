package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// Coroutine Builder는 반환값을 가지는 경우가 많은데
// Coroutine Builder인 launch{}는 Job 객체를 반환하며 이를 통해 종료될 때까지 기다리거나(join) 취소하는 조작을 할 수 있다.

suspend fun doOneTwoThree() = coroutineScope {
    val job = launch {
        println("launch1: ${Thread.currentThread().name}")
        delay(1000L)
        println("3!")
    }
    job.join() // job이 종료될때까지 CoroutineScope는 중단된다.
    launch {
        println("launch2: ${Thread.currentThread().name}")
        println("1!")
    }
    launch {
        println("launch3: ${Thread.currentThread().name}")
        delay(500L)
        println("2!")
    }
    println("4! : ${Thread.currentThread().name}")
}

fun main() = runBlocking {
    doOneTwoThree()
    println("runBlocking: ${Thread.currentThread().name}")
    print("5!")
}