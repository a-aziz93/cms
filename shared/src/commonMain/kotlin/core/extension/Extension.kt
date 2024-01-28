package core.extension

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import kotlin.time.Duration

suspend fun Duration.repeat(task: suspend () -> Unit): Job {
    val duraion = this
    withContext(Dispatchers.Default) {
        while (true) {
            task()
            delay(duraion.inWholeMilliseconds)
        }
    }
}