package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.async

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

// 아래 코드에서는 suspend function이 동기적으로 실행되면서 효율적이지 않은 코드야
// -> async_ex1.kt에서 병렬적으로 suspend function이 실행 될 수 있게 해주는 방법 있음.

// Coroutine은 기본적으로 하나의 Thread에서 여러 Coroutine이 번갈아 가며 실행되는 방식으로 동작한다.
// 하지만 특정 Dispatcher를 사용해서 다른 Thread에서 실행 할 수 있게 할 수 있다.
suspend fun getRandom1() : Int {
    delay(1000L)
    return Random.nextInt(0 , 500)
}
suspend fun getRandom2() : Int {
    delay(1000L)
    return Random.nextInt(0 , 500)
}
fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val value1 = getRandom1()
        val value2 = getRandom2()
        println("${value1} + ${value2} = ${value1 + value2}")
    }
    println(elapsedTime)
}