package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.launch

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

// 코루틴은 기본적으로 계층적인 구조를 가지고 있다.
// 상위 코루틴은 하위 코루틴을 끝까지 책임을 진다.

fun main() {
    runBlocking { // 계층적 , 구조적 : runBlocking{}이 cancel이 되면 자식 launch 두개도 캔슬된다.
        launch {
            println("launch1 : ${Thread.currentThread().name}")
            delay(1000L) // main()을 차단하지 않은 상태로 코루틴을 1초동안 지연시킨다.
            println("3!")
        }
        launch {
            println("launch2 : ${Thread.currentThread().name}")
            println("1!")
        }
        println("runBlocking : ${Thread.currentThread().name}")
        delay(500L) // main()을 차단하지 않은 상태로 코루틴을 1초동안 지연시킨다.
        println("2!")
    }
    println("4!")
}
// 위 코드는 runBlocking 안에 두개의 launch가 속한채 계층화 되어있는 구조입니다.
// runBlocking은 자신 안에 포함된 두개의 launch{}가 종료되기 전까지 종료되지 않습니다.

