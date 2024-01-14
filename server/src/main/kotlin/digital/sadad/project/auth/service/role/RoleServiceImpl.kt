package digital.sadad.project.auth.service.role

import digital.sadad.project.auth.entity.UserEntity
import digital.sadad.project.auth.repository.RoleRepository
import digital.sadad.project.core.config.AppConfig
import io.github.reactivecircus.cache4k.Cache
import mu.two.KotlinLogging
import org.koin.core.annotation.Single

private val logger = KotlinLogging.logger {}

@Single
class RoleServiceImpl(
    private val appConfig: AppConfig,
    private val roleRepository: RoleRepository,
) : RoleService {

    // Configure the Cache with the options of every entity in the cache
    // by default
    val roleCache by lazy {
        var cacheBuilder = Cache.Builder<Long, UserEntity>()
        val roleCacheConfig = appConfig.config.cache?.get("role")
        if (roleCacheConfig != null) {
            if (roleCacheConfig.maximumCacheSize != null) {
                cacheBuilder = cacheBuilder.maximumCacheSize(roleCacheConfig.maximumCacheSize)
            }
            if (roleCacheConfig.expireAfterAccess != null) {
                cacheBuilder = cacheBuilder.expireAfterAccess(roleCacheConfig.expireAfterAccess)
            }
            if (roleCacheConfig.expireAfterWrite != null) {
                cacheBuilder = cacheBuilder.expireAfterWrite(roleCacheConfig.expireAfterWrite)
            }
        }
        cacheBuilder.build()
    }


}