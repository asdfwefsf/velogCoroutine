package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.launch

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        println("launch : ${Thread.currentThread().name}")
        println("world!")
    }
    println("runBlocking : ${Thread.currentThread().name}")
    Thread.sleep(500L)
    println("Hello")
}

// 결과를 보면 알 수 있듯이 Thread.sleep()은 해당 CoroutineScope가 중단 되더라도 , 다른 Coroutine Scope에게
// 제어권을 넘겨주지 않는다. 이것이 Thread.sleep()과 delay의 차이점이다.
// delay()는 양보를 하고 Thread.sleep()은 양보를 하지 않는다는 것이다.

