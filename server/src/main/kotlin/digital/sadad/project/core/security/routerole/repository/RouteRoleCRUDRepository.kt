package digital.sadad.project.core.security.routerole.repository

import core.security.routerole.model.entity.RouteRoleEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class RouteRoleCRUDRepository(
    client: R2dbcSqlClient,
    table: Table<RouteRoleEntity>,
) : KotysaCRUDRepository<RouteRoleEntity, Long>(client, table)