package com.company.velogcoroutine.Coroutine.`2-Job_3-Cancel,TimOut`

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// launch{}에 할당된 자원이 있을때 Coroutine이 취소가 된다면 할당된 자원들도 마땅히 해제가 되어야 한다.

// suspend function은 JobCancellationException을 발생시키기 때문에 'try' 'catch' 'finally' 이 세 가지로 대응 할 수 있다.

suspend fun doOneTwoThree7() = coroutineScope {
    val job1 = launch {
        try {
            println("launch1: ${Thread.currentThread().name}")
            delay(1000L)
            println("3!")
        } catch (e: Exception) {
            println("Error occurred: ${e.message}")
        } finally {
            println("job1 is finishing!")
            // finally 코드 블락에서 할당된 자원을 해제하는 코드를 작성해준다.
        }
    }
    val job2 = launch {
        try {
            println("launch2: ${Thread.currentThread().name}")
            delay(1000L)
            println("1!")
        } finally {
            println("job2 is finishing!")
            // finally 코드 블락에서 할당된 자원을 해제하는 코드를 작성해준다.
        }
    }
    val job3 = launch {
        try {
            println("launch3: ${Thread.currentThread().name}")
            delay(1000L)
            println("2!")
        } finally {
            println("job3 is finishing!")
        }
    }

    delay(800L)
    job1.cancel()
    job2.cancel()
    job3.cancel()
    println("4!")
}
fun main() = runBlocking {
    doOneTwoThree7()
}