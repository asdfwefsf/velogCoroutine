package com.company.velogcoroutine.Coroutine.`7-1-SharedObject`

import kotlinx.coroutines.*
import kotlin.system.*

suspend fun massiveRun2(action: suspend() -> Unit) {
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
@Volatile // @Volatile 어노테이션을 사용해서 지정한 값은 어떤 스레드에서 변경을 해도 다른 Thread에게 값이 영향을 준다.
var counter2 = 0

fun main() = runBlocking {
    withContext(Dispatchers.Default) {
        massiveRun2 {
            counter2++
        }
    }
    // Counter = 100000 이라고 출력을 희망하지만 그렇지 않다.
    // 가시성이라는 것은 다른 스레드의 Coroutine에서 값을 수정했을 때 또 다른 스레드의 코루틴에서 값을 제대로 볼 수 있어야 되는 것이야.
    // @Volatile 어노테이션으로 인해서 100,000개의 코루틴이 가시성은 생겼으나 다수의 코루틴이 같은 값을 +1씩 증가 시키는 문제는 여전히 존재한다. (증가가 무시됨)
    println("Counter = ${counter2}")
}