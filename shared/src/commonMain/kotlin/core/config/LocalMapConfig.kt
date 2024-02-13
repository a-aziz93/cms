package core.config

object LocalMapConfig : ConfigSource, ConfigTarget {
    private val backingMap: MutableMap<String, Any> = mutableMapOf()

    override suspend fun get(key: String): Any? = backingMap[key]

    override suspend fun set(key: String, value:Any) {
        backingMap[key] = value
    }

    override suspend fun clear(key: String) = backingMap.clear()

}