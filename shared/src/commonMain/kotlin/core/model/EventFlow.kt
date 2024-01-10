package core.model

import kotlinx.coroutines.flow.Flow

interface EventFlow<T> {
    val events: Flow<T>
}