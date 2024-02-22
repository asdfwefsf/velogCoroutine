package com.company.velogcoroutine.Coroutine.`7-1-SharedObject`

import java.util.concurrent.atomic.*
import kotlin.system.*
import kotlinx.coroutines.*

// 가시성 문제를 '@Volatile'를 통해서 해결 하였으나 여전히 동시성의 문제는 남아있다.
// 스레드에 안전한 자료형 : 해당 값을 스레드에서 사용 할 때 다른 스레드는 간섭 못해
// 1. 자료형을 Atomic 관련 자료형으로 선언 , 2. incrementAndGet()을 함수를 사용한다.
// 모든 문제에 적용되지 않는다.

suspend fun massiveRun3(action: suspend () -> Unit) {
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
    println("$elapsed ms동안 ${n * k}개의 액션을 수행했습니다.")
}
val counter3 = AtomicInteger()

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun3 {
            counter3.incrementAndGet()
        }
    }
    println("Counter = $counter3")
}