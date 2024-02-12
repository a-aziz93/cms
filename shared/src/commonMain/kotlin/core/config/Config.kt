package core.config

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import java.util.*
import kotlin.NoSuchElementException
import kotlin.collections.ArrayList
import kotlin.reflect.KClass
import java.text.SimpleDateFormat
import java.util.Locale

abstract class Config(
    val sources: List<ConfigSource>,
    val targets: List<ConfigTarget>,
) : ConfigRegistry {

    private val _changes = MutableStateFlow<ConfigChangeEvent<*>?>(null)
    val changes: Flow<ConfigChangeEvent<*>> = _changes.filterNotNull()


    val configItems: List<ConfigItem<*>> = ArrayList()

    override fun <T : Any> registerItem(item: ConfigItem<T>) {
        // Ensure the key of the item is unique
        val duplicateKeyEntry = configItems.find { it.key == item.key }

        check(duplicateKeyEntry == null) {
            "The key \"${item.key}\" already in use by item with metadata \"${duplicateKeyEntry?.metadata}\" - please choose another."
        }

        (configItems as MutableList).add(item)
    }

    override suspend fun <T : Any> getValueOf(item: ConfigItem<T>, itemClass: KClass<T>): T {
        val mapper = resolveAdapterForClass(itemClass)

        val configSourceValue = sources.firstNotNullOfOrNull { it.get(item.key) }
            ?.let(mapper.fromString)

        return configSourceValue ?: item.defaultValue
    }

    override suspend fun <T : Any> setValueOf(item: ConfigItem<T>, itemClass: KClass<T>, newValue: T) {
        // Store old value
        val oldValue: T = getValueOf(item, itemClass)

        val mapper = resolveAdapterForClass(itemClass)

        // Override value

        targets.forEach {
            it.set(item.key, newValue.let(mapper.toString))
        }


        // Emit change
        _changes.tryEmit(
            ConfigChangeEvent(
                key = item.key,
                oldValue = oldValue,
                newValue = newValue,
                metadata = item.metadata
            )
        )
    }

    /**
     * Function for clearing local overrides, if an override handler is specified
     */
    fun clearOverrides() {
        // Collate all changes
        val changeEvents = overrideHandler.all
            .mapNotNull { entry ->
                val item = configItems.firstOrNull { it.key == entry.key }

                // If the item is null, the override key no longer exists in config. It may have been removed
                // between app versions. As such, we should clear the override fo this key as it's now redundant.
                if (item == null) {
                    overrideHandler.clear(entry.key)
                    null
                } else {
                    item to entry.value
                }
            }
            .map { (item, currentValue) ->
                ConfigChangeEvent(
                    key = item.key,
                    oldValue = currentValue,
                    newValue = item.defaultValue.toString(),
                    metadata = item.metadata
                )
            }

        // Clear items
        overrideHandler.all
            .map { it.key }
            .forEach(overrideHandler::clear)

        // Publish events
        changeEvents.forEach {
            _changes.tryEmit(it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T : Any> resolveAdapterForClass(clazz: KClass<T>): TypeAdapter<T> =
        DEFAULT_TYPE_ADAPTERS.firstOrNull { it.clazz == clazz } as? TypeAdapter<T>
            ?: throw NoSuchElementException("Type argument $clazz is not supported by konfigure.")

    /**
     * Type adapter definition used mapping types from [T] to [String] synchronously
     */
    internal data class TypeAdapter<T : Any>(
        val clazz: KClass<T>,
        val toString: (T) -> String,
        val fromString: (String) -> T
    )

    companion object {

        const val DATE_FORMAT_ISO_8601 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"

        val configDateFormat = SimpleDateFormat(DATE_FORMAT_ISO_8601, Locale.ENGLISH)

        private val DEFAULT_TYPE_ADAPTERS = hashSetOf(
            TypeAdapter(
                clazz = Boolean::class,
                fromString = String::toBoolean,
                toString = Boolean::toString
            ),
            TypeAdapter(
                clazz = Char::class,
                fromString = { it.toCharArray()[0] },
                toString = Char::toString
            ),
            TypeAdapter(
                clazz = Short::class,
                fromString = String::toShort,
                toString = Short::toString
            ),
            TypeAdapter(
                clazz = Int::class,
                fromString = String::toInt,
                toString = Int::toString
            ),
            TypeAdapter(
                clazz = Long::class,
                fromString = String::toLong,
                toString = Long::toString
            ),
            TypeAdapter(
                clazz = Float::class,
                fromString = String::toFloat,
                toString = Float::toString
            ),
            TypeAdapter(
                clazz = Double::class,
                fromString = String::toDouble,
                toString = Double::toString
            ),
            TypeAdapter(
                clazz = String::class,
                fromString = { it },
                toString = { it }
            ),
            TypeAdapter(
                clazz = Date::class,
                fromString = { configDateFormat.parse(it) },
                toString = { configDateFormat.format(it) }
            )
        )
    }
}