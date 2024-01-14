package digital.sadad.project.auth.repository

import digital.sadad.project.auth.entity.RoleTable
import digital.sadad.project.auth.entity.RoleEntity
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

    override fun getId(entity: RoleEntity): Long? = entity.id

    override fun create(entity: RoleEntity, username: String?, dateTime: LocalDateTime) = entity.copy(
        createdBy = username,
        createdAt = dateTime,
    )

    override fun update(entity: RoleEntity, username: String?, dateTime: LocalDateTime) = entity.copy(
        updatedBy = username,
        updatedAt = dateTime,
    )

    override fun checkSelectIdEquality(
        select: CoroutinesSqlClientSelect.FromTable<RoleEntity, RoleEntity>,
        id: Long
    ): CoroutinesSqlClientSelect.Return<RoleEntity> = select where RoleTable.id eq id

    override fun checkModifyIdEquality(
        select: CoroutinesSqlClientDeleteOrUpdate.FirstDeleteOrUpdate<RoleEntity>,
        id: Long
    ): CoroutinesSqlClientDeleteOrUpdate.Return = select where RoleTable.id eq id
}
