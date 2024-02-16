package core.config.model

import core.eventflow.StateModel
import kotlinx.coroutines.flow.MutableStateFlow

data class ConfigItem<T : Any>(
    val value: T?,
    private val defaultValue: T?,
    val metadata: ConfigMetadata?,
) : StateModel<T>(value) {
    fun default() =
        update(defaultValue)
}