package core.config

import kotlin.reflect.KClass

interface ConfigRegistry {

    val parent: ConfigRegistry?
        get() = null

    val group: String?
        get() = null

    fun <T : Any> registerItem(item: ConfigItem<T>)

    suspend fun <T : Any> getValueOf(item: ConfigItem<T>, itemClass: KClass<T>): T

    suspend fun <T : Any> setValueOf(item: ConfigItem<T>, itemClass: KClass<T>, newValue: T)
}