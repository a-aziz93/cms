package core.config

data class ConfigItem<T>(
    val key: String,
    val defaultValue: T,
    val description: String?,
    val metadata: ConfigMetadata
)