package com.company.velogcoroutine.Coroutine.`6-CEH_SupervisorJob`

// GlobalScope : 어디에도 속하지 않지만 원래부터 존재하는 전역 GlobalScope가 있다. GlobalScope를 사용하면 Coroutine을 쉽게 만들 수 있다.
// Job()을 활용해서 만든 코루틴을 제외한 일반적인 코루틴과 다르게 계층적으로 관리되지 않고 GlobalScope에서 수행이 된다.
// 이 GlobalScope를 이용하면 코루틴을 쉽게 수행 할 수 있다는 장점이 있지만 ,
// 그러나 어떤 Scope에도 속하지 않고 계층적인 구조를 가지고 있지 않기 때문에 , 관리하기 어렵다는 단점이 존재한다. 따라서 일반적으로는 GlobalScope는 잘 사용되지 않는다.
import kotlinx.coroutines.*
import kotlin.random.Random

suspend fun printRandom1() {
    delay(500L)
    println(Random.nextInt(0 , 500))
}

fun main() {
    val job = GlobalScope.launch(Dispatchers.IO) {
        launch { printRandom1() }
    }
    // Thread.sleep(1000L)을 사용한 이유는 main이 runBlocking이 아니기 때문에 delay() 함수를 사용 할 수 가 없다.
    Thread.sleep(1000L)
}