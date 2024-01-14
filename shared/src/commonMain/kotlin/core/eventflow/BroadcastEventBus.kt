package core.eventflow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

// That’s where the concept of aSharedFlow comes in. A shared flow exists regardless of whether it is being collected or not. A collector of the shared flow is called a subscriber. All subscribers of a shared flow receive the same sequence of values. It effectively works like a “broadcast channel”, without most of the channel overhead. It makes the concept of a broadcast channel obsolete.
class BroadcastEventBus<T>(
    replay: Int = 0,
    extraBufferCapacity: Int = 0,
    onBufferOverflow: BufferOverflow = BufferOverflow.SUSPEND
) : EventFlow<T> {
    private val _events = MutableSharedFlow<T>(
        replay,
        extraBufferCapacity,
        onBufferOverflow
    )
    override val events = _events.asSharedFlow() // read-only public view

    suspend fun postEvent(event: T) {
        _events.emit(event) // suspends until subscribers receive it
    }
}