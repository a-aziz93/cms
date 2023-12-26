package digital.sadad.project.auth.repository

import digital.sadad.project.auth.entity.RoleTable
import digital.sadad.project.auth.entity.UserTable
import digital.sadad.project.auth.model.Role
import digital.sadad.project.auth.model.User
import digital.sadad.project.core.database.service.DatabaseService
import mu.two.KotlinLogging
import org.ufoss.kotysa.CoroutinesSqlClientDeleteOrUpdate
import org.ufoss.kotysa.CoroutinesSqlClientSelect
import org.ufoss.kotysa.R2dbcSqlClient
import java.time.LocalDateTime
import java.util.*

private val logger = KotlinLogging.logger {}

class RoleRepositoryImpl(
    databaseService: DatabaseService,
    override val client: R2dbcSqlClient = databaseService.client,
    override val table: RoleTable,
) : RoleRepository {

    override fun getId(entity: Role): Long? = entity.id

    override fun create(entity: Role, username: String?, dateTime: LocalDateTime) = entity.copy(
        createdBy = username,
        createdAt = dateTime,
    )

    override fun update(entity: Role, username: String?, dateTime: LocalDateTime) = entity.copy(
        updatedBy = username,
        updatedAt = dateTime,
    )

    override fun checkSelectIdEquality(
        select: CoroutinesSqlClientSelect.FromTable<Role, Role>,
        id: Long
    ): CoroutinesSqlClientSelect.Return<Role> = select where RoleTable.id eq id

    override fun checkModifyIdEquality(
        select: CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<Role>,
        id: Long
    ): CoroutinesSqlClientDeleteOrUpdate.Return = select where RoleTable.id eq id
}
