package core.config

interface ConfigTarget {
    suspend fun set(key: List<String>, value: Any?)

    suspend fun clear(vararg key: String)

    suspend fun clear()
}