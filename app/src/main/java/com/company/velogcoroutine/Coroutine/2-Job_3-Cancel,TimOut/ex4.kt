package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.*
// 취소가 불가능한 Job 객체도 존재한다. -> ex6.kt에서 취소하는 방법있음.
// 아래 코드에서 Coroutine이 취소가 되지 않는 이유는 Coroutine 설계의 구조적인 문제이다.
// 아래 코드의 Coroutine은 Coroutine의 내부에서 suspend function이 호출된게 아니기 때문에

suspend fun doCount4() = coroutineScope {
    // parameter로 아무것도 받지 않는 launch의 경우에는 MainThread에서 실행하게 되어있다.
    val job1 = launch {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L
        while (i <=10) {
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTime) {
                delay(1000L)
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }
    delay(2000L)
    job1.cancel()
    println("doCount Done")
}
fun main() = runBlocking {
    doCount4()
}