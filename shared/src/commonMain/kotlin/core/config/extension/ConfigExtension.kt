package core.config.extension

import core.config.ReadWriteConfigRegistrar
import core.config.StateFlowConfigRegistrar
import core.config.model.ConfigMetadata
import kotlin.reflect.KProperty

typealias MetadataProvider = (KProperty<*>) -> ConfigMetadata

@Suppress("unused") // Unused suppression as it's used for extension function scoping
inline fun <reified T : Any> config(
    key: String? = null,
    defaultValue: T? = null,
    metadata: ConfigMetadata? = null,
): ReadWriteConfigRegistrar<T> =
    ReadWriteConfigRegistrar(
        key = key,
        defaultValue = defaultValue,
        metadata,
    )

@Suppress("unused") // Unused suppression as it's used for extension function scoping
inline fun <reified T : Any> configFlow(
    key: String? = null,
    defaultValue: T? = null,
    metadata: ConfigMetadata? = null,
): StateFlowConfigRegistrar<T> =
    StateFlowConfigRegistrar(
        key = key,
        defaultValue = defaultValue,
        metadata,
    )