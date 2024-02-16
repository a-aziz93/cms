package core.config

interface ConfigSource {
    suspend fun hasKey(vararg key: String): Boolean
    suspend fun get(vararg key: String): Any?
}