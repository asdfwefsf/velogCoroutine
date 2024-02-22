 package com.company.velogcoroutine.Coroutine.`6-CEH_SupervisorJob`

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import kotlin.random.Random



suspend fun getRandom4_1() : Int {
    delay(1000L)
    return Random.nextInt(0 , 500)
}

suspend fun getRandom4_2() : Int {
    delay(500L)
    throw ArithmeticException()
}

val ceh4 = CoroutineExceptionHandler {_ , exception ->
    println("Something happend : $exception")
}

fun main() = runBlocking {
    val job = launch(ceh4 + Job()) {// 여기의 Job()은 runBlocking이 취소 안 되게 보장해줌.
        val a = async { getRandom4_1() }
        val b = async(Job()) { getRandom4_2() } // 여기의 Job()은 a가 취소 안되게 보장해줌.
        println(a.await())
        println(b.await()) // 여기서 ArithmeticException이 한 번 더 발생한다. 그래서 launch(ceh4)에 Job()을 더해준거야. 만약 b를 launch로 만들었으면 launch(ceh4)만 사용해도 괜찮아.
    }
    delay(2000L)
}

// 근데 너무 불편해 이렇게 하면 그래서 ex5.kt에서 SupervisiorJob()을 사용한거야.