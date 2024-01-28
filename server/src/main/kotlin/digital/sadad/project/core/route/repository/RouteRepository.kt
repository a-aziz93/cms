package digital.sadad.project.core.route.repository

import core.route.model.entity.RouteEntity
import core.crud.repository.CRUDRepository
import core.role.model.entity.RoleEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient
import org.ufoss.kotysa.Table

class RouteRepository(
    client: R2dbcSqlClient,
    table: Table<RouteEntity>,
) : KotysaCRUDRepository<RouteEntity, Long>(client, table)