package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

// 특정 Coroutine은 취소가 불가능한 Coroutine으로 만들어줘야 할 수 도 있다.
// 위 내용을 가능하게 해주는 것이 withContext(NonCancellable)이다.
// 정불안하면 finally 절에 withContext(NonCancellable)을 넣어주면 된다.

// withContext는 launch , async , runBlocking 처럼 새로운 코루틴을 만들지 않고 이미 존재하는 코루틴의 컨텍스트를
// 변경하는 역할만 수행하므로 코루틴 빌더가 아니다. suspendFunction에 불과하다.
suspend fun doOneTwoThree8() = coroutineScope {
    val job1 = launch {
        withContext(NonCancellable) {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        }
        delay(1000L)
        print("job1: end")
    }
    val job2 = launch {
        withContext(NonCancellable) {
            println("launch2: ${Thread.currentThread().name}")
            delay(1000L)
            println("2!")
        }
        delay(1000L)
        print("job2: end")

    }
    val job3 = launch {
        withContext(NonCancellable) {
            println("launch3: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        }
        delay(1000L)
        print("job3: end")
    }
    delay(800L)
    // job.cancel()이 호출된 시점에 , withContext(NonCancellable) {} 아래 코드는 모두 취소된다.
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}
fun main() = runBlocking {
    doOneTwoThree8()
    println("runBlocking: ${Thread.currentThread().name}")
    println("5!")
}