package core.config

import core.config.model.ConfigItem

abstract class Config(
    final override val parent: ConfigRegistry? = null,
    final override val key: String? = null,
    final override val sources: List<ConfigSource>,
    final override val targets: List<ConfigTarget>,
) : ConfigRegistry {

    private val configItemMap = mutableMapOf<String, ConfigItem<*>>()

    override fun <T : Any> addConfigItem(key: String, item: ConfigItem<T>) {
        configItemMap[key] = item
    }

    override fun <T : Any> getConfigItem(key: String): ConfigItem<T> {
        checkConfigItemExists(key)

        return configItemMap[key] as ConfigItem<T>
    }

    override suspend fun <T : Any> get(key: String, defaultValue: T?): T? {
        checkConfigItemExists(key)

        val keys = listOfNotNull(this.key, key).toTypedArray()

        return withParentSources().firstOrNull { it.hasKey(*keys) }.let {
            if (it == null) defaultValue else it.get(key) as? T
        }
    }

    override suspend fun <T : Any> set(key: String, value: T?) {
        checkConfigItemExists(key)

        val keys = listOfNotNull(this.key, key)

        withParentTargets().forEach {
            it.set(keys, value)
        }

        (configItemMap[key]!! as ConfigItem<T>).update(value)
    }

    override suspend fun <T : Any> clear(key: String) {
        checkConfigItemExists(key)

        val keys = listOfNotNull(this.key, key).toTypedArray()

        withParentTargets().forEach { it.clear(*keys) }

        configItemMap[key]!!.default()
    }

    override suspend fun <T : Any> clear() {
        withParentTargets().forEach { it.clear() }

        configItemMap.values.forEach { it.default() }
    }

    private fun checkConfigItemExists(key: String) = check(configItemMap.containsKey(key)) {
        "ConfigItem with key \"${key}\" doesn't exists."
    }

    private fun withParentSources(): List<ConfigSource> {
        return emptyList()
    }

    private fun withParentTargets(): List<ConfigTarget> {
        return emptyList()
    }
}

