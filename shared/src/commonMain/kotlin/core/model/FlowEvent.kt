package core.model

import kotlinx.coroutines.flow.Flow

interface FlowEvent<T> {
    val events: Flow<T>
}