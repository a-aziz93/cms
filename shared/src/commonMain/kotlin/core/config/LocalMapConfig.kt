package core.config

object LocalMapConfig : ConfigSource, ConfigTarget {
    private val backingMap: MutableMap<String, String> = mutableMapOf()

    override suspend fun get(key: String): String? = backingMap[key]

    override suspend fun set(key: String, value: String) {
        backingMap[key] = value
    }

    override suspend fun clear(key: String) = backingMap.clear()

}