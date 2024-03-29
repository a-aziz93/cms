package digital.sadad.project.core.config.model.cache

import kotlin.time.Duration

data class CacheConfig(
    val name: String,
    val maximumCacheSize: Long? = null,
    val expireAfterAccess: Duration? = null,
    val expireAfterWrite: Duration? = null,
)
