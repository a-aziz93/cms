package digital.sadad.project.core.user.repository

import core.user.model.entity.UserEntity
import core.crud.repository.CRUDRepository
import core.role.model.entity.RoleEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class UserRepository(
    client: R2dbcSqlClient,
    table: Table<UserEntity>,
) : KotysaCRUDRepository<UserEntity, Long>(client, table)