package core.config

import kotlin.reflect.KClass
import kotlin.reflect.KProperty

typealias MetadataProvider = (KProperty<*>) -> ConfigMetadata

class ConfigRegistrar<T : Any>(
    private val key: String? = null,
    private val defaultValue: T? = null,
    private val description: String? = null,
    private val metadataProvider: MetadataProvider? = null,
    private val itemClass: KClass<T>
) {

    operator fun provideDelegate(thisRef: ConfigRegistry, property: KProperty<*>): ConfigDelegate<T> {
        val key = key ?: property.name
        val default = defaultValue ?: itemClass.defaultValue
        val metadata = metadataProvider?.invoke(property) ?: object : ConfigMetadata {}

        val configItem = ConfigItem(key, default, description, metadata)
        thisRef.registerItem(configItem)

        // Provide this class as the delegate
        return object : ConfigDelegate<T> {
            override suspend fun getValue(thisRef: ConfigRegistry, property: KProperty<*>): T =
                thisRef.getValueOf(configItem, itemClass)

            override suspend fun setValue(thisRef: ConfigRegistry, property: KProperty<*>, value: T) =
                thisRef.setValueOf(configItem, itemClass, value)
        }
    }
}


inline fun <reified T : Any> config(
    key: String? = null,
    defaultValue: T? = null,
    description: String? = null,
    metadata: ConfigMetadata? = null,
    noinline metadataProvider: MetadataProvider? = when (metadata) {
        null -> null
        else -> {
            { metadata }
        }
    }
): ConfigRegistrar<T> =
    ConfigRegistrar(
        key,
        defaultValue,
        description,
        metadataProvider,
        T::class,
    )