package core.config

import core.config.model.ConfigItem

abstract class Config(
    val sources: List<ConfigSource>,
    val targets: List<ConfigTarget>,
) : ConfigRegistry {

    val configMap = mutableMapOf<String, ConfigItem<Any?>>()

    override suspend fun <T : Any> set(key: String, value: T?) {

        check(configMap.containsKey(key)) {
            "The key \"${key}\" doesn't exists in configs - please choose another."
        }

        targets.forEach {
            it.set(key, value)
        }

        configMap[key]!!.update(value)

    }

    override suspend fun <T : Any> clear(key: String) {
        check(configMap.containsKey(key)) {
            "The key \"${key}\" doesn't exists in configs - please choose another."
        }

        targets.forEach { it.clear(key) }

        configMap[key]?.default()
    }

    override suspend fun <T : Any> clear() {
        targets.forEach { it.clear() }
        configMap.values.forEach { it.default() }
    }
}

