package core.config

interface ConfigTarget {
    suspend fun set(key: String, value: String)

    suspend fun clear(key: String)
}