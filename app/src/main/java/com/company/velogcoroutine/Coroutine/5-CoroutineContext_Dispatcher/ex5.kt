package com.company.velogcoroutine.Coroutine.`5-CoroutineContext_Dispatcher`

// 아래는 계층적인 코루틴의 예시이다.
import kotlinx.coroutines.*
import kotlin.system.*

//fun main() = runBlocking {
//    val elapsed = measureTimeMillis {
//        val job = launch {
//            launch {
//                println("launch1: ${Thread.currentThread().name}")
//                delay(5000L)
//            }
//            launch {
//                println("launch2: ${Thread.currentThread().name}")
//                delay(10L)
//            }
//        }
//        job.join()
//    }
//    println(elapsed) // 상위 코루틴이 하위 코루틴이 끝날때까지 기다려서 5초 이후에 최상위 코루틴이 종료된다.
//}
//
