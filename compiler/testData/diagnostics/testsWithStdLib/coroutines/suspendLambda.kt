// COMMON_COROUTINES_TEST
import COROUTINES_PACKAGE.Continuation
import COROUTINES_PACKAGE.CoroutineContext
import COROUTINES_PACKAGE.startCoroutine

fun bar() {
    suspend {
        println()
    }.startCoroutine(object: Continuation<Unit> {
        override val context: CoroutineContext
            get() = TODO("not implemented")

        override fun resume(value: Unit) {
            TODO("not implemented")
        }

        override fun resumeWithException(exception: Throwable) {
            TODO("not implemented")
        }
    })
}
