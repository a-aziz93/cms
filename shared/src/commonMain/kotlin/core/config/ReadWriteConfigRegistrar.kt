package core.config

import core.config.model.ConfigItem
import core.config.model.ConfigMetadata
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KProperty

class ReadWriteConfigRegistrar<T : Any>(
    private val key: String? = null,
    private val defaultValue: T? = null,
    private val metadata: ConfigMetadata? = null,
) {
    operator fun provideDelegate(thisRef: ConfigRegistry, property: KProperty<*>): ReadWriteConfigDelegate<T> {
        val key = key ?: property.name

        thisRef.addConfigItem(
            key, ConfigItem(
                runBlocking { thisRef.get(key, defaultValue) },
                defaultValue,
                metadata,
            )
        )

        return object : ReadWriteConfigDelegate<T> {
            override fun getValue(thisRef: ConfigRegistry, property: KProperty<*>): T? =
                thisRef.getConfigItem<T>(key).value

            override fun setValue(thisRef: ConfigRegistry, property: KProperty<*>, value: T?) =
                runBlocking { thisRef.set(key, value) }
        }
    }
}