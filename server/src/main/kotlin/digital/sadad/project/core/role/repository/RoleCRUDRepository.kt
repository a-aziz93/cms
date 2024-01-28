package digital.sadad.project.core.role.repository

import core.role.model.entity.RoleEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class RoleCRUDRepository(
    client: R2dbcSqlClient,
    table: Table<RoleEntity>,
) : KotysaCRUDRepository<RoleEntity, Long>(client, table)