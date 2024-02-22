package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.launch

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    launch {
        println("launch : ${Thread.currentThread().name}")
        println("delay1")
        delay(450L)
        launch {
//            delay(500L)
            println("delay2")
        }
    }
    println("runBlocking : ${Thread.currentThread().name}")
    delay(200L)
    println("delay3")
}



