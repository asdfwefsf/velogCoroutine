package com.company.velogcoroutine.Coroutine.`7-1-SharedObject`

import kotlinx.coroutines.*
import kotlin.system.*

// 여러 thread를 코루틴이 사용하기 때문에 동시성 문제가 발생한다.
// 객체의 값을 바라보는 코루틴들의 시선이 다를 수 있어서 '공유객체'라는 것을 사용한다.
//

suspend fun massiveRun(action: suspend() -> Unit) {
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

var counter = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun {
            counter ++
        }
    }
    // Counter = 100000 이라고 출력을 희망하지만 그렇지 않다.
    // 가시성이라는 것은 하나의 Coroutine에서 값을 수정했을 때 다른 쪽에서 값을 제대로 볼 수 있어야 되는 것이야.
    // 왜냐하면 counter에 값을 더할 때 기준값을 같은 값으로 보고 올릴 수 있기 때문이다.
    println("Counter = $counter")
}