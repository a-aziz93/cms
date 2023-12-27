package digital.sadad.project.auth.repository

import de.nycode.bcrypt.hash
import de.nycode.bcrypt.verify
import digital.sadad.project.auth.entity.RoleTable
import digital.sadad.project.auth.entity.UserTable
import digital.sadad.project.auth.model.Role
import digital.sadad.project.auth.model.User
import digital.sadad.project.core.database.service.DatabaseService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.koin.core.annotation.Singleton
import org.ufoss.kotysa.CoroutinesSqlClientDeleteOrUpdate
import org.ufoss.kotysa.CoroutinesSqlClientSelect
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.ValueProvider
import java.time.LocalDateTime

private const val BCRYPT_SALT = 12

@Singleton
class UsersRepositoryImpl(
    databaseService: DatabaseService,
    override val client: R2dbcSqlClient = databaseService.client,
    override val table: UserTable,
) : UsersRepository {

    override fun getId(entity: User): Long? = entity.id

    override fun create(entity: User, username: String?, dateTime: LocalDateTime) = entity.copy(
        password = hash(entity.password, BCRYPT_SALT).decodeToString(),
        createdBy = username,
        createdAt = dateTime,
    )

    override fun update(entity: User, username: String?, dateTime: LocalDateTime) = entity.copy(
        updatedBy = username,
        updatedAt = dateTime,
    )

    override fun checkSelectIdEquality(
        select: CoroutinesSqlClientSelect.FromTable<User, User>,
        id: Long
    ): CoroutinesSqlClientSelect.Return<User> = select where UserTable.id eq id

    override fun checkModifyIdEquality(
        select: CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<User>,
        id: Long
    ): CoroutinesSqlClientDeleteOrUpdate.Return = select where UserTable.id eq id

    override suspend fun save(vararg users: User, username: String?) = save(listOf(*users), { entity, update ->
        update
            .set(UserTable.name).eq(entity.name)
            .set(UserTable.username).eq(entity.username)
            .set(UserTable.password).eq(entity.password)
            .set(UserTable.email).eq(entity.email)
            .set(UserTable.avatar).eq(entity.avatar)
            .set(UserTable.roleId).eq(entity.roleId)
            .set(UserTable.updatedBy).eq(entity.updatedBy)
            .set(UserTable.updatedAt).eq(entity.updatedAt)
            .where(UserTable.id).eq(entity.id!!)
    }, username)

    override suspend fun findWithRole(id: Long): Pair<User, Role>? =
        find({
            withRole(it)
        }) {
            it innerJoin RoleTable on UserTable.roleId eq RoleTable.id where UserTable.id eq id
        }.fetchFirstOrNull()

    override suspend fun findWithRole(username: String): Pair<User, Role>? =
        find({
            withRole(it)
        }) {
            it innerJoin RoleTable on UserTable.roleId eq RoleTable.id where UserTable.username eq username
        }.fetchFirstOrNull()

    override suspend fun findWithRole(username: String, password: String): Pair<User, Role>? =
        withContext(Dispatchers.IO) {
            return@withContext findWithRole(username)?.let {
                if (verify(password, it.first.password.encodeToByteArray())) {
                    return@withContext it
                }
                return@withContext null
            }
        }

    private fun withRole(valueProvider: ValueProvider) = Pair(
        User(
            valueProvider[UserTable.id],
            valueProvider[UserTable.name]!!,
            valueProvider[UserTable.email]!!,
            valueProvider[UserTable.username]!!,
            valueProvider[UserTable.password]!!,
            valueProvider[UserTable.roleId]!!,
            valueProvider[UserTable.avatar],
            valueProvider[UserTable.active]!!,
            valueProvider[UserTable.createdBy],
            valueProvider[UserTable.createdAt],
            valueProvider[UserTable.updatedBy],
            valueProvider[UserTable.updatedAt],
        ), Role(
            valueProvider[RoleTable.id],
            valueProvider[RoleTable.label]!!,
            valueProvider[RoleTable.description],
            valueProvider[RoleTable.createdBy],
            valueProvider[RoleTable.createdAt],
            valueProvider[RoleTable.updatedBy],
            valueProvider[RoleTable.updatedAt],
        )
    )
}