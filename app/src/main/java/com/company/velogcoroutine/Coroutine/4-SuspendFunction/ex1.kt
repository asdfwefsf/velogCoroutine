package com.company.velogcoroutine.Coroutine.`4-SuspendFunction`

// delay()

// job.join()

// withContext()
// withContext()의 코드 블락을 새로운 코루틴을 만들어서 동작을 시키는데 , 이 코루틴이 실행되는 동안에
// 밖에 있는 블록에 suspension이 들어가게 된다. runBlocking과 비슷해 보이지만 runBlocking은 Thread를 차단 시키는 것이고
// withContext()는 바로 밖에 있는 Coroutine을 차단하는 것이야.


// yield()
// withTimeout()