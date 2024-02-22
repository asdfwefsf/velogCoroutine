package com.company.velogcoroutine.Coroutine.`5-CoroutineContext_Dispatcher`

import kotlinx.coroutines.*

// CoroutineElement 간의 결합 : '+' 연산자를 사용해서.
// 이렇게 합쳐진 Coroutine들은 'coroutineContext[???]'를 통해서 조회 할 수 있다.

// 이런 식으로 Coroutine Context를 조금만 수정해도 다양한 형태로 코루틴을 실행 할 수 있다.

// 상위 코루틴의 컨텍스트와 하위 코루틴의 컨텍스트를 합친 것이 하위 코루틴의 컨텍스트
@OptIn(ExperimentalStdlibApi::class)
fun main() = runBlocking {
    launch {
        launch(Dispatchers.IO + CoroutineName("launch1")) {
            println("launch1: ${Thread.currentThread().name}")
            println(coroutineContext[CoroutineDispatcher])
            println(coroutineContext[CoroutineName])
            delay(5000L)
        }
        launch(Dispatchers.Default + CoroutineName("launch2")) {
            println("launch2: ${Thread.currentThread().name}")
            println(coroutineContext[CoroutineDispatcher])
            println(coroutineContext[CoroutineName])
            delay(10L)
        }
    }
}