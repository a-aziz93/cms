package digital.sadad.project.core.repository.role

import digital.sadad.project.auth.entity.role.RoleEntity
import digital.sadad.project.auth.model.entity.role.RoleTable
import digital.sadad.project.core.database.service.DatabaseService
import org.ufoss.kotysa.CoroutinesSqlClientDeleteOrUpdate
import org.ufoss.kotysa.CoroutinesSqlClientSelect
import org.ufoss.kotysa.R2dbcSqlClient
import java.time.LocalDateTime

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