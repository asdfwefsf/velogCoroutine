package com.company.velogcoroutine.Coroutine.`5-CoroutineContext_Dispatcher`

import kotlinx.coroutines.*

// 'CoroutineScope' 와 'CoroutineContext'는 구조화되어 있고 부모에게 계층적으로 되어 있습니다.
// CoroutineContext의 Job 역시 부모에게 의존적이다. 부모 Coroutine을 취소하면 자식 Coroutine도 취소된다.

// 아래 코드에서는 부모가 있는 Job과 없는 Job에 관한 예시 코드이다.
// 기본적으로는 Coroutine을 만들게 되면 , 부모의 Coroutine을 상속 받는다. 이는 Coroutine 들이 계층적 구조를
// 이루어서 상속 받는 다는 것을 알 수 있다.
// 그러나 기본적인 Coroutine 이외에 launch(Job())을 이용해서 Coroutine을 만들게 되면 더 이상 계층적 구조가 아니게 된다.
// 왜냐하면 기본적으로 만들어지는 job은 누가 부모인지 알지만, Job()은 누가 부모인지 모른다.
// 구조적인 동시성이 있을때는 상위 코루틴이 하위 코루틴들이 모두 완료 될 때까지 기다리고 , 하위 코루틴 중 누군가 취소되면
// 상위 코루틴도 취소가 된다. launch로 Job()을 활용해서 Coroutine을 만들면 생성된 Coroutine은
// 더 이상 구조적인 동시성을 갖는 코루틴이 아니게 된다.(Job()은 누가 부모 형제 코루틴인지 알 수 없기 때문에)
// 더 이상 구조적인 동시성을 갖는 코루틴이 아니라는 것은 의미상으로는 더 이상 상위 코루틴이 하위 코루틴을 기다려주지 않고 ,
// 하위 코루틴이 취소 되어도 형제 코루틴과 상위 코루틴이 취소되지 않는다는거야 (사실은 독립적인 코루틴이라 상위 , 하위 , 형제 코루틴이 없지만)

//fun main() = runBlocking {
//    val job = launch {
//        launch(Job()) {
//            println(coroutineContext[Job])
//            println("launch1: ${Thread.currentThread().name}")
//            delay(1000L)
//            println("3!")
//        }
//
//        launch {
//            println(coroutineContext[Job])
//            println("launch2: ${Thread.currentThread().name}")
//            delay(1000L)
//            println("1!")
//        }
//    }
//    delay(500L)
//    job.cancelAndJoin()
//    delay(1000L)
//}