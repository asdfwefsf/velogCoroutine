package com.company.velogcoroutine.Coroutine.`5-CoroutineContext_Dispatcher`

// async에서 Coroutine Dispatcher 활용하기
// launch 이외에 async , withContext 등에도 Coroutine Dispatcher를 사용 할 수 있다.

import kotlinx.coroutines.*

//fun main() = runBlocking<Unit> {
//    async {
//        println("부모의 콘텍스트 / ${Thread.currentThread().name}")
//    }
//    async(Dispatchers.Default) {
//        println("Default / ${Thread.currentThread().name}")
//    }
//    async(Dispatchers.IO) {
//        println("IO / ${Thread.currentThread().name}")
//    }
//    async(Dispatchers.Unconfined) {
//        println("Unconfined / ${Thread.currentThread().name}")
//        delay(100L)
//        println("Unconfined / ${Thread.currentThread().name}")
//    }
//    async(newSingleThreadContext("Geonhee Hwang")) {
//        println("newSingleThreadContext / ${Thread.currentThread().name}")
//    }
//}