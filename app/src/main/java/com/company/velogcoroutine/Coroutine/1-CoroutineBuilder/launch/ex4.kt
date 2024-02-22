package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.launch

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        println("launch1 : ${Thread.currentThread().name}")
        delay(1000L) // suspension point
        println("3! ${Thread.currentThread().name}")
    }
    launch {
        println("launch2 : ${Thread.currentThread().name}")
        println("1! ${Thread.currentThread().name}")
    }
    println("runBlocking : ${Thread.currentThread().name}")
    delay(500L) // suspension point
    println("2! ${Thread.currentThread().name}")
}
// Coroutine은 단일 Thread를 사용하는 경우에도 , 서로 양보하면서 실행되기 때문에 매우 유용하다.

// main() 함수가 실행이 되면 ,
// [1] 1번째 launch의 Coroutine Scope가 실행 되도록 예약(no.1)이 걸린다.(Coroutine Scope가 잠이 들어 : suspension point) :
// runBlocking의 CoroutineScope는 delay가 있는 경우를 제외하고는 내부의 코드 블락이 전부 실행되기 전까지 다른 CoroutineScope가 실행되는 것을 막는다.
// [2] 2번째 launch의 Coroutine Scope가 실행 되도록 예약(no.2)이 걸린다.(Coroutine Scope가 잠이 들어 : suspension point) :
// runBlocking의 CoroutineScope는 delay가 있는 경우를 제외하고는 내부의 코드 블락이 전부 실행되기 전까지 다른 CoroutineScope가 실행되는 것을 막는다.
// [3] 따라서 처음에 출력되는 값은 17번째 줄이다.
// [4] delay(500L)이 실행되면 runBlocking의 CoroutineScope는 스스로 멈추고 , 다른 Coroutine Scope에게 제어권을
// 넘기게 된다. : runBlocking{}의 Coroutine Scope의 delay() 밑의 코드를 무시한다. : ignore section
// [5] 그러면 1번째 launch의 CoroutineScope가 실행되고 , delay() 밑의 코드는 무시한채 2번째 launch의 Coroutine Scope로
// 제어권이 넘어가게된다.
// 그러다가 runBlocking{}의 CoroutineScope의 delay가 풀리면서 2가 출력되고 1번째 launch의 CoroutineScope가 실행되면서 3이 출력된다.

