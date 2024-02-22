package com.company.velogcoroutine.Coroutine.`6-CEH_SupervisorJob`

// CoroutineScope는 인자로 CoroutineContext를 받는데 Coroutine Element(Coroutine Context의 구성요소 3중1)를 하나만 넣어도 되고
// Coroutine Element를 더해서 CoroutineContext를 만들어서 인자로 넣어줘도 된다.

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.*

suspend fun printRandom2() {
    delay(500L)
    println(Random.nextInt(0 , 500))
}

@OptIn(ExperimentalStdlibApi::class)
fun main() {
    val scope = CoroutineScope(Dispatchers.Default + CoroutineName("Scope"))
    val job = scope.launch(Dispatchers.IO) {// 이처럼 launch를 활용해서 Dispatchers 변경 쌉가능
        launch { printRandom2() }
        println(coroutineContext[CoroutineDispatcher])
        println(coroutineContext[CoroutineName])
    }
    Thread.sleep(1000L)
}