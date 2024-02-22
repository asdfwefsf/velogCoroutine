package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.runBlocking

import kotlinx.coroutines.runBlocking

//fun <T>println(msg : T) {
//    kotlin.io.println("$msg [${Thread.currentThread().name}]")
//}

fun main() = runBlocking {
    // 'Thread.currentThread().name'을 활용해서 현재 사용되고 있는 코루틴의 이름을 알 수 있다.
    println(Thread.currentThread().name)
    println("Hello")
}

// runBlocking은 자신의 코드 블록이 종료되기 전까지 다른 Coroutine이 실행되는 것을 막는다.
