package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.async

// async를 호출하는 순간 async로부터 생성된 Coroutine Scope의 내부코드들은 실행을 예약한다.
// 그렇다면 조금 늦게 실행되게 하고 싶으면 어떻게 해야 할까
// 그럴때 사용하는 것이 async(start = CoroutineStart.LAZY)이다. 실행 예약을 하고 싶으면 .start() 메서드를 사용하면 된다.
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

suspend fun getRandom1_3() : Int {
    delay(1000L)
    return Random.nextInt(0 , 500)
}

suspend fun getRandom2_3() : Int {
    delay(1000L)
    return Random.nextInt(0 , 500)
}

fun main() = runBlocking {
    val elapsedTime = measureTimeMillis {
        val value1 = async ( start = CoroutineStart.LAZY ) {
            getRandom1_3()
        }
        val value2 = async ( start = CoroutineStart.LAZY ) {
            getRandom2_3()
        }
        value1.start()
        value2.start() // .start()를 주석 처리하면 .await()이 호출되는 시점에 value2가 실행된다.
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    }
    println(elapsedTime)
}