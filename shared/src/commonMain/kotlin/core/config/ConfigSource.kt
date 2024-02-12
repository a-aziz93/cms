package core.config

interface ConfigSource {
    suspend fun get(key: String): String?
}