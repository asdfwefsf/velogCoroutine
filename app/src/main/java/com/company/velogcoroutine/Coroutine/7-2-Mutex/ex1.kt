package com.company.velogcoroutine.Coroutine.`7-2-Mutex`

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

// Mutex : Mutual Exclusion : 상호배제의 줄임말이야.
// 공유 상태를 수정할 때 임계 영역(critical section)을 이용하게 하며 , 임계 영역은 스레드가 동시에 접근하는 것을 허용하지 않는다.

suspend fun massiveRun(action: suspend () -> Unit) {
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

val mutex = Mutex()
var counter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            mutex.withLock { // critical section을 만드는 부분으로 , 여러 스레드 중 하나의 스레드 만 'counter++'를 실행 할 수 있다.
                counter++
            }
        }
    }
    println("Counter = $counter")
}






