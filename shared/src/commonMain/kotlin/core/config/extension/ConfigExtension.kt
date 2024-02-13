package core.config.extension

import core.config.Config
import core.config.model.ConfigItem
import core.config.model.ConfigMetadata
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

inline fun <reified T : Any> Config.config(
    key: String,
    defaultValue: T? = null,
    metadata: ConfigMetadata? = null,
): StateFlow<T?> {

    var value = defaultValue

    runBlocking {
        sources.firstOrNull { it.hasKey(key) }?.let { value = it.get(key) as T? }
    }

    configMap[key] = ConfigItem(
        value,
        defaultValue,
        metadata,
    )

    return configMap[key]!!.events as StateFlow<T?>
}