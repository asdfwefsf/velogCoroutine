package com.company.velogcoroutine.Coroutine.`5-CoroutineContext_Dispatcher`

import kotlinx.coroutines.*

// CoroutineContext란 , Coroutine을 다룰 방법을 정해준다.
// CoroutineContext의 구성요소는 Coroutine Name(코루틴 이름) + Job(코루틴 동작관리 , 생명주기 관리) + Dispatchers(코루틴이 실행될 스레드 관리)



// Coroutine은 여러 디스패처 Default , Io , Unconfined , newSingleThreadContext 가진다.
// Dispatcher는 Thread에  Coroutine을 보내는 역할을 한다. 각각의 Dispatcher는 자신이 관리하는 ThreadPool을
// 가지고 있으며 , 임의의 Thread에 Coroutine을 배분한다.

// Dispatcher.Main : 특정 스레드 풀에서 수행하지 않고 안드로이드 애플리케이션의 메인 스레드(UI Thread)에서 작업을 수행한다.
// Dispatcher.IO : 코어 수 보다 훨씬 많은 스레드를 가진 스레드 풀에서 수행한다. (IO 작업) -> CPU를 덜 소모하기 때문에.
// Dispatcher.Default : 코어 수에 비례하는 스레드를 가진 스레드 풀에서 수행한다. (복잡한 연산 같은 백그라운드 작업) -> 코어는 한 번에 하나의 일밖에 못하니까
// newSingleThreadContext(name : String) : 새로운 스레드 1개를 만들어서 수행한다. -> 무조건 Thread를 받아서 코루틴을 실행하려고 할 때
// newFixedThreadPoolContext(nThreads : Int , name : String) : nThreads 개수의 ThreadPool을 만들어서 name을 지어서 수행한다.

// Dispatcher.Unconfined : 시작은 부모 Thread에서 하지만 앞으로는 어디에서 수행 될 지 모른다.(문제야..비추)

//@OptIn(DelicateCoroutinesApi::class)
//fun main() = runBlocking<Unit> {
//    launch {
//        // launch에 아무 Dispatchers도 넣지 않았을 경우에는 부모의 컨텍스트에서 실행이 된다.
//        println("부모의 콘텍스트 / ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Default){
//        println("Default / ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.IO) {
//        println("IO / ${Thread.currentThread().name}")
//    }
//    launch(Dispatchers.Unconfined) {
//        // Unconfined : Main Thread에서 호출
//        println("Unconfined / ${Thread.currentThread().name}")
//    }
//    launch(newSingleThreadContext("Geonhee Hwang")) {
//        // newSingleThreadContext : 새로운 Thread를 만들어서 사용하라는 의미야.
//        println("newSingleThreadContext / ${Thread.currentThread().name}")
//    }
//    launch(newFixedThreadPoolContext(12,"Twelve")) {
//        println("newFixedThreadPoolContext / ${Thread.currentThread().name}")
//    }
//}


