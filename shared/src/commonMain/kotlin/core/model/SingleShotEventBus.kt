package core.model

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

// Channels are used to handle events that must be processed exactly once* (see sidenote below for details). This happens in a design with a type of event that usually has a single subscriber, but intermittently (at startup or during some kind of reconfiguration) there are no subscribers at all, and there is a requirement that all posted events must be retained until a subscriber appears.
class SingleShotEventBus<T>(
    capacity: Int = Channel.RENDEZVOUS,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND,
    onUndeliveredElement: ((T) -> Unit)? = null
): EventFlow<T> {
    private val _events = Channel<T>(
        capacity,
        onBufferOverflow,
        onUndeliveredElement
    )
    override val events = _events.receiveAsFlow() // expose as flow

    suspend fun postEvent(event: T) {
        _events.send(event) // suspends on buffer overflow
    }
}