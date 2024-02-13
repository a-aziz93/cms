package core.config

interface ConfigTarget {
    suspend fun set(key: String, value: Any?)

    suspend fun clear(key: String)

    suspend fun clear()
}