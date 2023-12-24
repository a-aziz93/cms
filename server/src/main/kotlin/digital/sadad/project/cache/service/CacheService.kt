package digital.sadad.project.cache.service

import digital.sadad.project.config.AppConfig
import io.github.reactivecircus.cache4k.Cache
import digital.sadad.project.user.model.User

/**
 * Cache Service
 * @property myConfig AppConfig Configuration of our service
 */

@Singleton
class CacheService(
    private val myConfig: AppConfig,
) {
    // Configure the Cache with the options of every entity in the cache
    // by default
    val users by lazy {
        Cache.Builder<Long, User>().build()
    }
}