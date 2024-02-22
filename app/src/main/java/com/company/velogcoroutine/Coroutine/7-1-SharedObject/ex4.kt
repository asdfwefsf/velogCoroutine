package com.company.velogcoroutine.Coroutine.`7-1-SharedObject`

import kotlinx.coroutines.*
import kotlin.system.*

// Thread를 단일 스레드로 한정 시킨 후 문제 해결하는 방법
// : 모든 코드에서 단일 스레드에서 사용 할 수 있고, 일부의 스레드에서 사용 할 수 도 있다. ( 상황에 따라 다르다. )

suspend fun massiveRun4(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val elapsed = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("$elapsed ms 동안 ${n * k}개의 액션을 수행했습니다.")
}
var counter4 = 0
val counterContext = newSingleThreadContext("CounterContext")

fun main() = runBlocking {
    withContext(counterContext) {
        massiveRun4 { counter4 ++ }
    }
    println("Counter = ${counter4}")
}