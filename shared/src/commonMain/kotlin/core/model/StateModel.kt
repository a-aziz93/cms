package core.model

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

// A popular way to deal with buffer overflow is to drop the oldest events and retain only the most recent, newest events. In particular, it is a great way to model state variables in an application. It is such a widespread use-case that it has its own specialized StateFlow type which serves as a replacement for a ConflatedBroadcastChannel, which became obsolete, too.
class StateModel<T>(initial: T): EventFlow<T> {
    private val _state = MutableStateFlow(initial)
    override val events = _state.asStateFlow() // read-only public view

    fun update(newValue: T) {
        _state.value = newValue // NOT suspending
    }
}