package digital.sadad.project.core.userrole.repository

import core.userrole.model.entity.UserRoleEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class UserRoleCRUDRepository(
    client: R2dbcSqlClient,
    table: Table<UserRoleEntity>,
) : KotysaCRUDRepository<UserRoleEntity, Long>(client, table)