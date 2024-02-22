package com.company.velogcoroutine.Coroutine.`7-3-Actor`

import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.system.measureTimeMillis

suspend fun massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val elapsed = measureTimeMillis {
        coroutineScope {
            repeat(n) {
                launch {
                    repeat(k) { action() }
                }
            }
        }
    }
    println("$elapsed ms 동안 ${n * k}개의 액션을 수행했습니다.")
}
// Actor : Actor가 독점적으로 자료를 가지게 되고 그 자료를 다른 코루틴과 공유하지 않고 그 자료를 이용하기 위해서는 무조건 Actor를 통해서만 접근할 수 있다.
// 자료를 관리하는 Actor를 만들고 , 그 액터에게 신호를 보내서 우리가 원하는 결과를 얻는 형태
// Actor를 사용하는 과정[1.sealed class 생성]
sealed class CounterMsg {
    object IncCounter : CounterMsg() // Actor에게 값을 증가시키는 신호 보내
    class GetCounter(val response: CompletableDeferred<Int>) : CounterMsg() // Actor에게 값을 가져오라는 신호 보내
}
fun CoroutineScope.countActor() = actor<CounterMsg> {// actor 내부에 상태를 캡슐화 시켜서 다른  코루틴이 접근하지 못하게 한다.
    var counter = 0
    // suspension point : 값이 오기까지 기다렸다가 값이 오면 깨어난다. (아래 코드)
    for (msg in channel) { // 외부에서 보내는 것은 채널을 통해서만 받을 수 있다.(recieve) : 한쪽에서는 데이터를 보내고 다른쪽에서는 데이터를 받을 수 있는 것.
        // channel은 송신 측에서 갑을 보낼 수 있고 , 수신 측에서 값을 받을 수 있는 도구이다.
        when (msg) {
            is CounterMsg.IncCounter ->counter ++
            is CounterMsg.GetCounter -> msg.response.complete(counter)
        }
    }
}
// ** 주는 쪽도 받는 쪽이 끝날때까지 기다렸다가 깨어나고 , 받는 쪽도 주는 쪽이 없으면 잠이 들었다가 깨어난다. ** //
fun main() = runBlocking<Unit> {
    val counter = countActor()
    withContext(Dispatchers.Default) {
        massiveRun {
            counter.send(CounterMsg.IncCounter) // countActor()를 사용하기 위해서는 오로지 채널을 통해서 시그널을 보내야 한다. : actor가 직접 값을 더하게 한다.
        }
    }
    val response = CompletableDeferred<Int>()
    counter.send(CounterMsg.GetCounter(response)) // countActor()를 사용하기 위해서는 오로지 채널을 통해서 시그널을 보내야 한다. : actor가 값을 가져온다.
    println("Counter = ${response.await()}") // suspension point
    counter.close() // 0
}