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
import mu.two.KotlinLogging
import org.koin.core.annotation.Singleton
import org.ufoss.kotysa.CoroutinesSqlClientDeleteOrUpdate
import org.ufoss.kotysa.CoroutinesSqlClientSelect
import org.ufoss.kotysa.R2dbcSqlClient
import java.time.LocalDateTime

private val logger = KotlinLogging.logger {}
private const val BCRYPT_SALT = 12

@Singleton
class UsersRepositoryImpl(
    databaseService: DatabaseService,
    override val client: R2dbcSqlClient = databaseService.client,
    override val table: UserTable,
) : UsersRepository {

    override fun getId(entity: User): Long? = entity.id

    override fun create(entity: User, username: String?, dateTime: LocalDateTime) = entity.copy(
        password = hash(entity.password, 12).decodeToString(),
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

    override suspend fun find(username: String): User? =
        find {
            it where UserTable.username eq username
        }.fetchFirstOrNull()


    override suspend fun find(username: String, password: String): User? =
        withContext(Dispatchers.IO) {
            return@withContext find(username)?.let {
                if (verify(password, it.password.encodeToByteArray())) {
                    return@withContext it
                }
                return@withContext null
            }
        }
}