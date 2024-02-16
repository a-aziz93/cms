package core.config

import core.config.model.ConfigItem
import core.config.model.ConfigMetadata
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KProperty

class StateFlowConfigRegistrar<T : Any>(
    private val key: String? = null,
    private val defaultValue: T? = null,
    private val metadata: ConfigMetadata? = null,
) {
    operator fun provideDelegate(thisRef: ConfigRegistry, property: KProperty<*>): StateFlowConfigDelegate<T> {
        val key = key ?: property.name

        thisRef.addConfigItem(
            key, ConfigItem(
                runBlocking { thisRef.get(key, defaultValue) },
                defaultValue,
                metadata,
            )
        )

        return object : StateFlowConfigDelegate<T> {
            override fun getValue(thisRef: ConfigRegistry, property: KProperty<*>): StateFlow<T?> =
                thisRef.getConfigItem<T>(key).events

        }
    }
}