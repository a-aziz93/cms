package digital.sadad.project.core.security.role.service

import digital.sadad.project.core.security.role.repository.RoleCRUDRepository
import org.ufoss.kotysa.R2dbcSqlClient

class RoleServiceImpl(
    override val crudRepository: digital.sadad.project.core.security.role.repository.RoleCRUDRepository,
) : digital.sadad.project.core.security.role.service.RoleService {
}