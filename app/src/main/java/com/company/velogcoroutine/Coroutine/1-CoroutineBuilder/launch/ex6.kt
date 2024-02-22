package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.launch

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// suspend function : 중단 가능한 함수
// 다른 Coroutine 또는 suspend function을 호출 할 수 있다.
// coroutine 내부의 코드를 간단하게 함수로 분리하고 싶으면 suspend 키워드를 사용하면 된다.
suspend fun doThree() {
    println("launch3 : ${Thread.currentThread().name}")
    delay(1000L)
    println("3!")
}

fun doOne() {
    println("launch1: ${Thread.currentThread().name}")
    println("1!")
}

suspend fun doTwo() {
    println("runBlocking: ${Thread.currentThread().name}")
    delay(5000L)
    println("2!")
}

fun main() = runBlocking {
    launch { doThree() }
    launch { doOne() }
    doTwo()
}

// delay는 코루틴||suspend function 내부에서만 호출이 가능하다.
