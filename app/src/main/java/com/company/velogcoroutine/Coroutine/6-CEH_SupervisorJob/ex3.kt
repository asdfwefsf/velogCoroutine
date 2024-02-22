package com.company.velogcoroutine.Coroutine.`6-CEH_SupervisorJob`

// CEH(Coroutine Exception Handler : 코루틴 예외를 체계적으로 관리 할 수 있다.)
// CoroutineExceptionHandler를 이용해서 자신만의 CEH를 만든 다음 상위 코루틴의 컨텍스트에 등록한다.
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.*

suspend fun printRandom3_1() {
    delay(1000L)
    println(Random.nextInt(0 , 500))
}

suspend fun printRandom3_2() {
    delay(500L)
    throw ArithmeticException()
}

val ceh = CoroutineExceptionHandler { _ , exception -> // _자리는 coroutineContext 자리
    println("Something happend: $exception")
}

@OptIn(ExperimentalStdlibApi::class)
fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch(ceh + Dispatchers.Default) {
        launch { printRandom3_1() }
        launch { printRandom3_2() }
    }
    println(coroutineContext[CoroutineDispatcher])
    job.join()
}