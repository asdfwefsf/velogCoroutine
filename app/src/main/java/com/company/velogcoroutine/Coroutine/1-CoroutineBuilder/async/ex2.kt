package com.company.velogcoroutine.Coroutine.`1-CoroutineBuilder`.async

import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.system.measureTimeMillis

// async : Coroutine Builder야.
// async 키워드를 사용하면 동시에 다른 코드들을 수행 할 수 있다. : aync가 만드는 코드 블록은 비동기야
// 비동기라는 점과 단독으로 CoroutineScope를 만들 수 없다는 점이 launch와 비슷하다.
// 하지만 , async는 Coroutine의 수행 결과를 await 키워드를 통해서 받을 수 있다.
// await 키워드는 async 블록의 수행이 끝났는지 확인하고 끝났으면 반환값을 받아오고 만약 아직 끝나지 않았다면
// suspend 되었다가 나중에 받아온다.

// launch 반환값 : Job 객체 : .join() , .cancel()
// async 반환값 : Deffered 객체 : Job을 상속 받았으므로 .join() , .cancel() 메서드 사용 가능. + await을 통해서 수행 결과값을 받아 올 수 있다.
// 따라서 Coroutine Scope에서 도출되는 결과값을 받아야 한다면 async , 결과값을 받지 않아도 된다면 launch를 선택하면 된다.
suspend fun getRandom1_2() : Int {
    delay(1000L)
    return Random.nextInt(0 , 500)
}

suspend fun getRandom2_2() : Int {
    delay(1000L)
    return Random.nextInt(0 , 500)
}

fun main() = runBlocking{
    val elapsedTime = measureTimeMillis{
        // async를 사용한 이유는 getRandom1_2()를 호출하기 위한게 아니라 , 호출된 Coroutine의 결과값을 가져오기 위해서 사용한거야.
        val value1 = async { getRandom1_2() }
        val value2 = async { getRandom2_2() }
        // .await()은 job.join의 기능에 결과를 가져오는 기능이 추가 되었다고 생각하면 된다.
        // .await()도 suspension point이다.
        println("${value1.await()} + ${value2.await()} = ${value1.await() + value2.await()}")
    }
    println(elapsedTime)
}
