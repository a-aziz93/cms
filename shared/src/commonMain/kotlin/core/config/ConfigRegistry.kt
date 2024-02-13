package core.config

interface ConfigRegistry {
    val parent: ConfigRegistry?
        get() = null

    suspend fun <T : Any> set(key: String, value: T?)
    suspend fun <T : Any> clear(key: String)
    suspend fun <T : Any> clear()
}