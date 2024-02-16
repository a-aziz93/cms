package core.config

import core.config.model.ConfigItem

interface ConfigRegistry {

    val parent: ConfigRegistry?
        get() = null

    val key: String?

    val sources: List<ConfigSource>

    val targets: List<ConfigTarget>

    fun <T : Any> addConfigItem(key: String, item: ConfigItem<T>)

    fun <T : Any> getConfigItem(key: String): ConfigItem<T>

    suspend fun <T : Any> get(key: String, defaultValue: T? = null): T?

    suspend fun <T : Any> set(key: String, value: T?)

    suspend fun <T : Any> clear(key: String)

    suspend fun <T : Any> clear()
}