package core.config

import kotlinx.coroutines.flow.StateFlow
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty

interface StateFlowConfigDelegate<T : Any> : ReadOnlyProperty<ConfigRegistry, StateFlow<T?>>