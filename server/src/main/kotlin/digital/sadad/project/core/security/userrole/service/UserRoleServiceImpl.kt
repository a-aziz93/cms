package digital.sadad.project.core.security.userrole.service

import digital.sadad.project.core.security.userrole.repository.UserRoleCRUDRepository

class UserRoleServiceImpl(
    override val crudRepository: UserRoleCRUDRepository,
) : UserRoleService {
}