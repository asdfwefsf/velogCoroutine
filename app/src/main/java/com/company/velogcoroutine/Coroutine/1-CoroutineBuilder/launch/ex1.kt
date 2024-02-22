package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.launch

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        println("launch : ${Thread.currentThread().name}")
        println("World!")
    }
    println("runBlocking : ${Thread.currentThread().name}")
    println("Hello")
}

// launch{}는 runBlocking과 다르게 단독으로는 CoroutineScope를 구성 할 수 없다. : launch는 코루틴 내에서만 사용이 가능하다.
// runBlocking{}의 CoroutineScope : 내 CoroutineScope에 있는 코드가 종료되기 전까지 다른 코루틴 스코프의 실행을 막아!!
// launch{}의 CoroutineScope : 가능하다면 다른 코루틴 스코프와 병렬적으로 실행이 가능해!!

// 위 코드 실행 결과 해석
// runBlocking이 MainThread의 실행을 차단한 채로 해당 CoroutineScope 내부 코드가 진행된다.
// launch{} 코드 블락이 실행될 준비를 하는 동안 println()이 순차적으로 실행된다.