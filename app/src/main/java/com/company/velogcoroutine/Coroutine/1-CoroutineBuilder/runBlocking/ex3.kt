package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.runBlocking

import kotlinx.coroutines.runBlocking

fun main() = runBlocking{
    // runBlocking의 수신객체는 CoroutineScope이다. 따라서 코드 블록 내부에서 코루틴에 있는 모든 기능들을
    // 사용 할 수 있다.
    // ** 수신객체 : 'extension lambda'라고 불리는데 , 이는 람다를 확장한 것 처럼 사용 할 수 있기 때문이다.
    // 즉 , runBlocking의 코드 블록이 코루틴을 확장한 것 처럼 사용 할 수 있다는 의미야.
    println(this)
    println(Thread.currentThread().name)
    println("Hello")
}

// println(this)의 결과값으로 BlockingCoroutine{Active}라고 나올 텐데 이는 , BlockingCoroutine이 활성상태라는 것을 의미한다.
// BlockingCoroutine : runBlocking{}으로 부터 생성된 CoroutineScope의 객체이다.
// - Coroutine은 CoroutineScope 내부에서만 사용이 가능하다.