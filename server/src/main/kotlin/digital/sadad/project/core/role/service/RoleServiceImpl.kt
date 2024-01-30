package digital.sadad.project.core.role.service

import digital.sadad.project.core.role.repository.RoleCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient

class RoleServiceImpl(
    override val crudRepository: RoleCRUDRepository,
) : RoleService {
}