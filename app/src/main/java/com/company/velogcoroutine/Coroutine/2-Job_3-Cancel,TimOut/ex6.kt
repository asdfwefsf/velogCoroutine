package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// isActive : Coroutine은 this.isActive를 통해서 해당 Coroutine이 활성화 되어 있는지 확인 할 수 있다.
// isActivie는 Default 값이 true로 설정되어 있다.
suspend fun doCount6() = coroutineScope {
    val job1 = launch(Dispatchers.Default) {
        var i = 1
        var nextTime = System.currentTimeMillis() + 100L
        while ( i <= 10 && isActive) { // Coroutine이 활성화 되어 있는 상태에서만 while의 조건이 참이 되도록한거야.
            val currentTime = System.currentTimeMillis()
            if (currentTime >= nextTime) {
                println(i)
                nextTime = currentTime + 100L
                i++
            }
        }
    }
    delay(300L)
    job1.cancel()
    println("doCount Done")
}

fun main() = runBlocking {
    doCount6()
}
