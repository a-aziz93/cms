package core.config

data class ConfigChangeEvent<T>(
    val key: String,
    val oldValue: T,
    val newValue: T,
    val metadata: ConfigMetadata
)