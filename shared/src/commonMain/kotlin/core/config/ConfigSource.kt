package core.config

interface ConfigSource {
    suspend fun hasKey(key: String): Boolean
    suspend fun get(key: String): Any?
}