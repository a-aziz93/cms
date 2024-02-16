package core.config

object LocalMapConfig : ConfigSource, ConfigTarget {
    private val backingMap: MutableMap<String, Any?> = mutableMapOf()

    override suspend fun hasKey(vararg key: String): Boolean = backingMap.containsKey(key)

    override suspend fun get(vararg key: String): Any? = backingMap[key]

    override suspend fun set(vararg key: String, value: Any?) {
        backingMap[key] = value
    }

    override suspend fun clear(vararg key: String) {
        backingMap.remove(key)
    }

    override suspend fun clear() = backingMap.clear()

}