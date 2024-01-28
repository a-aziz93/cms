package digital.sadad.project.core.routerole.repository

import core.routerole.model.entity.RouteRoleEntity
import core.crud.repository.CRUDRepository
import core.role.model.entity.RoleEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class RouteRoleRepository(
    client: R2dbcSqlClient,
    table: Table<RouteRoleEntity>,
) : KotysaCRUDRepository<RouteRoleEntity, Long>(client, table)