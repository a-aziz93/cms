package digital.sadad.project.core.user.repository

import core.user.model.entity.UserEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class UserCRUDRepository(
    client: R2dbcSqlClient,
    table: Table<UserEntity>,
) : KotysaCRUDRepository<UserEntity, Long>(client, table)