package com.company.velogcoroutine.Coroutine.`6-CEH_SupervisorJob`

import kotlinx.coroutines.*
import kotlin.random.Random

// SupervisiorJob()은 예외가 발생하면 취소를 '아래방향으로만' 내린다. (cf. 일반적인 Job은 '위 아래 ' 둘 다 보내)
// 즉, 하위 코루틴에게만 예외를 보낸다는 것이다.
suspend fun printRandom5_1() {
    delay(1000L)
    println(Random.nextInt(0 , 500))
}

suspend fun printRandom5_2() {
    delay(500L)
    throw ArithmeticException()
}

val ceh5 = CoroutineExceptionHandler { _, exception ->
    println("Something happend: $exception")
}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob() + ceh5)
    val job1 = scope.launch { printRandom5_1() }
    val job2 = scope.launch { printRandom5_2() }
    joinAll(job1 , job2)
}