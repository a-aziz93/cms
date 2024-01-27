package digital.sadad.project.core.service.role

import digital.sadad.project.core.repository.role.RoleRepository

class RoleServiceImpl(
    override val crudRepository: RoleRepository,
) : RoleService {
}