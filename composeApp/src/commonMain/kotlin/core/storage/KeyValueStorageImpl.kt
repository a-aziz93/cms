package core.storage

import com.russhwolf.settings.*
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.serialization.decodeValue
import com.russhwolf.settings.serialization.decodeValueOrNull
import com.russhwolf.settings.serialization.encodeValue
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.modules.SerializersModule
import org.koin.core.annotation.Single
import kotlin.reflect.KClass

@Single
class KeyValueStorageImpl : KeyValueStorage {
    private val settings: Settings by lazy { Settings() }

    override fun set(key: String, value: Any) {
        when (value) {
            is Boolean -> settings.putBoolean(key, value)
            is Int -> settings.putInt(key, value)
            is Long -> settings.putLong(key, value)
            is Float -> settings.putFloat(key, value)
            is Double -> settings.putDouble(key, value)
            is String -> settings.putString(key, value)
        }
    }

    override fun <T : Any> get(key: String, kClass: KClass<T>): T? = when (kClass) {
        Boolean::class -> settings.getBooleanOrNull(key)
        Int::class -> settings.getIntOrNull(key)
        Long::class -> settings.getLongOrNull(key)
        Float::class -> settings.getFloatOrNull(key)
        Double::class -> settings.getDoubleOrNull(key)
        String::class -> settings.getStringOrNull(key)
        else -> null
    } as T?

    override fun <T : Any> get(key: String, kClass: KClass<T>, defaultValue: T): T = when (kClass) {
        Boolean::class -> settings.getBoolean(key, defaultValue as Boolean)
        Int::class -> settings.getInt(key, defaultValue as Int)
        Long::class -> settings.getLong(key, defaultValue as Long)
        Float::class -> settings.getFloat(key, defaultValue as Float)
        Double::class -> settings.getDouble(key, defaultValue as Double)
        else -> settings.getString(key, defaultValue as String)
    } as T


    @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
    override fun <T> set(
        serializer: KSerializer<T>,
        key: String,
        value: T,
        serializersModule: SerializersModule
    ): Unit {
        settings.encodeValue(serializer, key, value, serializersModule)
    }

    @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
    override fun <T> get(serializer: KSerializer<T>, key: String, serializersModule: SerializersModule): T? {
        return settings.decodeValueOrNull(serializer, key, serializersModule)
    }

    @OptIn(ExperimentalSerializationApi::class, ExperimentalSettingsApi::class)
    override fun <T> get(
        serializer: KSerializer<T>,
        key: String,
        defaultValue: T,
        serializersModule: SerializersModule
    ): T {
        return settings.decodeValue(serializer, key, defaultValue, serializersModule)
    }

    override fun remove(key: String): Unit {
        settings.remove(key)
    }

    // clean all the stored values
    override fun cleanStorage() {
        settings.clear()
    }
}