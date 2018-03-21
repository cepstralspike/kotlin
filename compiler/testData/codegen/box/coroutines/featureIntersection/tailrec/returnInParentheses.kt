// WITH_RUNTIME
// WITH_COROUTINES
// COMMON_COROUTINES_TEST
// IGNORE_BACKEND_WITHOUT_CHECK: JS
import helpers.*
import COROUTINES_PACKAGE.*

tailrec suspend fun foo(x: Int) {
    if (x == 0) return
    (return foo(x - 1))
}

fun builder(c: suspend () -> Unit) {
    c.startCoroutine(EmptyContinuation)
}

fun box(): String {
    builder {
        foo(1000000)
    }
    return "OK"
}