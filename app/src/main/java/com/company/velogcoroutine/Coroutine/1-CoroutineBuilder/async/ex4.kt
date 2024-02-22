package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.async

import kotlinx.coroutines.*
import kotlin.random.Random

// async를 사용해서 구조적인 동시성을 만들 수 있다.
suspend fun getRandom1_4() : Int {
    try {
        delay(1000L)
        return Random.nextInt(0 , 500)
    } finally {
        println("getRandom1 is cancelled")
    }
}
suspend fun getRandom2_4() : Int {
    delay(500L)
    throw IllegalStateException()
}
suspend fun doSomething() = coroutineScope {// 부모 코루틴 : 자식 코루틴이 취소되면 부모 코루틴도 취소된다.
    val value1 = async {// 자식 코루틴 1 : 자식 코루틴 2에서 에러가 발생 했으니까 Coroutine을 취소 하라고 알려준다.
                                       // 왜냐면 자식 코루틴 2와 자식 코루틴 1은 형제 코루틴이기 때문이다.
        getRandom1_4()
    }
    val value2 = async { getRandom2_4() } // 자식 코루틴 2 : 에러 발생
    try {
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    } finally {
        println("doSomething is cancelled.")
    }
}
fun main() = runBlocking {
    try {
        doSomething()
    } catch (e:IllegalStateException) {
        println("doSomething failed : $e")
    }
}