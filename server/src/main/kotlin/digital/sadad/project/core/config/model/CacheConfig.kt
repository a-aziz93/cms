package digital.sadad.project.core.config.model

import kotlin.time.Duration

data class CacheConfig(
    val maximumCacheSize: Long? = null,
    val expireAfterAccess: Duration? = null,
    val expireAfterWrite: Duration? = null,
)
