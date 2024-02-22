package com.company.velogcoroutine.Coroutine.`6-CEH_SupervisorJob`

import kotlinx.coroutines.*
import java.lang.ArithmeticException
import kotlin.random.Random

// SupervisiorScope

suspend fun printRandom6_1() {
    delay(1000L)
    println(Random.nextInt(0 , 500))
}

suspend fun printRandom6_2() {
    delay(500L)
    throw ArithmeticException()
}

suspend fun supervisoredFunc() = supervisorScope {
    // supervisorScope 코드 블락 내부에 에러가 발생할 곳에 반드시 ceh를 붙히거나 try-catch를 해야한다. 아니면 그냥 에러가 발생해버린다.
    launch { printRandom6_1() }
    launch(ceh6) { printRandom6_2() }
}

val ceh6 = CoroutineExceptionHandler { _, exception ->
    println("Something happend: $exception")
}

fun main() = runBlocking{
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch {
        supervisoredFunc()
    }
    job.join()
}