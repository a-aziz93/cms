package digital.sadad.project.user.repository

import de.nycode.bcrypt.hash
import de.nycode.bcrypt.verify
import digital.sadad.project.user.entity.UserTable
import digital.sadad.project.user.mapper.toEntity
import digital.sadad.project.user.mapper.toModel
import digital.sadad.project.user.model.User
import digital.sadad.project.database.service.DataBaseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import mu.two.KotlinLogging
import org.koin.core.annotation.Singleton
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}
private const val BCRYPT_SALT = 12

/**
 * Users Repository
 * @property dataBaseService Database service
 */
@Singleton
class UsersRepositoryImpl(
    private val dataBaseService: DataBaseService
) : UsersRepository {

    /**
     * Find all users in database
     * @return Flow<User> Flow of users
     * @see User
     */
    override suspend fun findAll(): Flow<User> = withContext(Dispatchers.IO) {
        logger.debug { "findAll" }

        return@withContext (dataBaseService.client selectFrom UserTable).fetchAll().map { it.toModel() }
    }

    /**
     * Get hashed password from a plain text password
     * @param password Plain text password
     * @return String Hashed password by Bcrypt algorithm
     */
    override fun hashedPassword(password: String) = Bcrypt.hash(password, BCRYPT_SALT).decodeToString()

    /**
     * Check if username and password are correct
     * @param username Username
     * @param password Password
     * @return User? User if username and password are correct, null otherwise
     */
    override suspend fun checkUserNameAndPassword(username: String, password: String): User? =
        withContext(Dispatchers.IO) {
            val user = findByUsername(username)
            return@withContext user?.let {
                if (verify(password, user.password.encodeToByteArray())) {
                    return@withContext user
                }
                return@withContext null
            }
        }

    /**
     * Find user by id
     * @param id User id
     * @return User? User if exists, null otherwise
     */
    override suspend fun findById(id: Long): User? = withContext(Dispatchers.IO) {
        logger.debug { "findById: Buscando usuario con id: $id" }

        return@withContext (dataBaseService.client selectFrom UserTable
                where UserTable.id eq id
                ).fetchFirstOrNull()?.toModel()
    }

    /**
     * Find user by username
     * @param username User username
     * @return User? User if exists, null otherwise
     */
    override suspend fun findByUsername(username: String): User? = withContext(Dispatchers.IO) {
        logger.debug { "findByUsername: Buscando usuario con username: $username" }

        return@withContext (dataBaseService.client selectFrom UserTable
                where UserTable.username eq username
                ).fetchFirstOrNull()?.toModel()
    }

    /**
     * Save or update user
     * @param entity User to save or update
     * @return User User saved or updated
     */
    override suspend fun save(entity: User): User = withContext(Dispatchers.IO) {
        logger.debug { "save: $entity" }

        if (entity.id == User.NEW_USER) {
            create(entity)
        } else {
            update(entity)
        }
    }

    /**
     * Create user
     * @param entity User to create
     * @return User created
     */
    suspend fun create(entity: User): User {
        val newEntity = entity.copy(
            password = hash(entity.password, 12).decodeToString(),
            createdAt = LocalDateTime.now(),
            updatedAt = LocalDateTime.now()
        ).toEntity()

        logger.debug { "create: $newEntity" }

        return (dataBaseService.client insertAndReturn newEntity).toModel()

    }

    /**
     * Update user
     * @param entity User to update
     * @return User User updated
     */
    suspend fun update(entity: User): User {
        logger.debug { "update: $entity" }
        val updateEntity = entity.copy(updatedAt = LocalDateTime.now()).toEntity()

        (dataBaseService.client update UserTable
                set UserTable.name eq updateEntity.name
                set UserTable.username eq updateEntity.username
                set UserTable.password eq updateEntity.password
                set UserTable.email eq updateEntity.email
                set UserTable.avatar eq updateEntity.avatar
                set UserTable.role eq updateEntity.role
                set UserTable.updatedAt eq updateEntity.updatedAt
                where UserTable.id eq entity.id)
            .execute()
        return updateEntity.toModel()
    }

    /**
     * Delete user
     * @param entity User to delete
     * @return User User deleted
     */
    override suspend fun delete(entity: User): User {
        logger.debug { "delete: $entity" }
        (dataBaseService.client deleteFrom UserTable
                where UserTable.id eq entity.id)
            .execute()
        return entity
    }

    /**
     * Delete all users
     * @return Unit
     */
    override suspend fun deleteAll() {
        logger.debug { "deleteAll" }
        dataBaseService.client deleteAllFrom UserTable
    }

    /**
     * Save all users
     * @param entities Iterable<User> Users to save
     * @return Flow<User> Flow of users
     */
    override suspend fun saveAll(entities: Iterable<User>): Flow<User> {
        logger.debug { "saveAll: $entities" }
        entities.forEach { save(it) }
        return this.findAll()
    }
}