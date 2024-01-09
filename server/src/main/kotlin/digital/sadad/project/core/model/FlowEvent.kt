package digital.sadad.project.core.model

import kotlinx.coroutines.flow.Flow

interface FlowEvent<T> {
    val events: Flow<T>
}