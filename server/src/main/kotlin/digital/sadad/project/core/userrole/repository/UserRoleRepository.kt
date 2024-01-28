package digital.sadad.project.core.userrole.repository

import core.userrole.model.entity.UserRoleEntity
import core.crud.repository.CRUDRepository
import core.role.model.entity.RoleEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class UserRoleRepository(
    client: R2dbcSqlClient,
    table: Table<UserRoleEntity>,
) : KotysaCRUDRepository<UserRoleEntity, Long>(client, table)