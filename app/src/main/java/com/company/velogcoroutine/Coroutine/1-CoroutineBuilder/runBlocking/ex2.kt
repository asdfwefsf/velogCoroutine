package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.runBlocking

import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    println(coroutineContext)
    println(Thread.currentThread().name)
    println("Hello")
}

// coroutineContext는 Coroutine Scope내에서 Coroutine을 처리하기 위한 정보를 말한다.
