package com.company.velogcoroutine.Coroutine.`5-CoroutineContext_Dispatcher`

import kotlinx.coroutines.*

// 'Dispatchers.Unconfined'는 처음에는 부모의 Thread에서 수행 되지만 , 한 번 suspension point를 거치면
// 다른 Thread에서 수행한다. 이때 , 어느 Thread에서 수행 될 지 예측 할 수 없다. 따라서 가능하면 확실한 Dispatchers의
// 사용을 권장한다.

//fun main() = runBlocking<Unit> {
//    async(Dispatchers.Unconfined) {
//        println("Unconfined / ${Thread.currentThread().name}")
//        delay(1000L)
//        println("Unconfined / ${Thread.currentThread().name}")
//        delay(1000L)
//        println("Unconfined / ${Thread.currentThread().name}")
//        delay(1000L)
//        println("Unconfined / ${Thread.currentThread().name}")
//    }
//}