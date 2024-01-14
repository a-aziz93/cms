package digital.sadad.project.auth.service.user

import com.github.michaelbull.result.*
import digital.sadad.project.auth.error.UserError
import digital.sadad.project.auth.entity.UserEntity
import digital.sadad.project.auth.repository.UserRepository
import digital.sadad.project.core.config.AppConfig
import io.github.reactivecircus.cache4k.Cache
import mu.two.KotlinLogging
import org.koin.core.annotation.Single

private val logger = KotlinLogging.logger {}

@Single
class UserServiceImpl(
    private val appConfig: AppConfig,
    private val usersRepository: UserRepository,
) : UserService {

    // Configure the Cache with the options of every entity in the cache
    // by default
    val users by lazy {
        var cacheBuilder = Cache.Builder<Long, UserEntity>()
        val userCacheConfig = appConfig.config.cache?.get("users")
        if (userCacheConfig != null) {
            if (userCacheConfig.maximumCacheSize != null) {
                cacheBuilder = cacheBuilder.maximumCacheSize(userCacheConfig.maximumCacheSize)
            }
            if (userCacheConfig.expireAfterAccess != null) {
                cacheBuilder = cacheBuilder.expireAfterAccess(userCacheConfig.expireAfterAccess)
            }
            if (userCacheConfig.expireAfterWrite != null) {
                cacheBuilder = cacheBuilder.expireAfterWrite(userCacheConfig.expireAfterWrite)
            }
        }
        cacheBuilder.build()
    }

    override suspend fun save(vararg users: UserEntity, username: String?): Result<List<UserEntity>, UserError> {
        logger.debug { "save: save user" }
        return findByUsername(user.username).onSuccess {
            return Err(UserError.BadRequest("Another user existe with this username: ${user.username}"))
        }.onFailure {
            return Ok(usersRepository.save(*users, "").also {
                cacheService.users.put(it.id, it)
            })
        }

    }


    /**
     * Find by id
     * @param id Long Id of user
     * @return Result<User, UserError> Result of user or error if not found
     */
    override suspend fun find(id: Long): Result<UserEntity, UserError> {
        logger.debug { "findById: search user by id" }

        // find in cache if not found in repository
        return cacheService.users.get(id)?.let {
            logger.debug { "findById: found in cache" }
            Ok(it)
        } ?: run {
            usersRepository.findById(id)?.let { user ->
                logger.debug { "findById: found in repository" }
                cacheService.users.put(id, user)
                Ok(user)
            } ?: Err(UserError.NotFound("User with id $id not found"))
        }
    }

    /**
     * Find by username
     * @param username String Username of user
     * @return Result<User, UserError> Result of user or error if not found
     */
    override suspend fun findByUsername(username: String): Result<UserEntity, UserError> {
        logger.debug { "findById: search user by username" }

        // find in cache if not found in repository
        return usersRepository.findByUsername(username)?.let { user ->
            logger.debug { "findById: found in repository" }
            cacheService.users.put(user.id, user)
            Ok(user)
        } ?: Err(UserError.NotFound("User with username: $username not found"))
    }

    /**
     * Check if username and password are valid
     * @param username String Username of user
     * @param password String Password of user
     * @return Result<User, UserError> Result of user or error if not found
     */
    override suspend fun findUserNameAndPassword(username: String, password: String): Result<UserEntity, UserError> {
        logger.debug { "checkUserNameAndPassword: check username and password" }

        return usersRepository.checkUserNameAndPassword(username, password)?.let {
            Ok(it)
        } ?: Err(UserError.BadCredentials("User password or username not valid"))
    }

    override suspend fun delete(id: Long): Result<Boolean, UserError> {
        logger.debug { "delete: delete" }
        // find, if exists delete in cache and repository and notify
        return Ok(usersRepository.delete(id).also {
            if (it) {
                cacheService.users.invalidate(id)
            }
        })
    }

    override suspend fun delete(): Result<Long, UserError> {
        TODO("Not yet implemented")
    }

    /**
     * Check if user is admin
     * @param id Long Id of user
     * @return Result<Boolean, UserError> Result of user or error if not found
     */
    override suspend fun isAdmin(id: Long): Result<Boolean, UserError> {
        logger.debug { "isAdmin: chek if user is admin" }
        return findById(id).andThen {
            if (it.role == UserEntity.Role.ADMIN) {
                cacheService.users.put(it.id, it)
                Ok(true)
            } else {
                Err(UserError.BadRole("User is not admin"))
            }
        }
    }

    /**
     * Update image of user
     * @param id Long Id of user
     * @param image String Image of user
     * @return Result<User, UserError> Result of user or error if not found
     */
    override suspend fun updateImage(id: Long, image: String): Result<UserEntity, UserError> {
        logger.debug { "updateImage: update image user" }

        // find, if exists update in cache and repository and notify
        return findById(id).andThen {
            Ok(usersRepository.save(
                it.copy(
                    avatar = image
                )
            ).also { res ->
                cacheService.users.put(id, res)
            })
        }
    }
}