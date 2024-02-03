package digital.sadad.project.core.security.userrole.service

import digital.sadad.project.core.security.userrole.repository.UserRoleCRUDRepository

class UserRoleServiceImpl(
    override val crudRepository: digital.sadad.project.core.security.userrole.repository.UserRoleCRUDRepository,
) : digital.sadad.project.core.security.userrole.service.UserRoleService {
}