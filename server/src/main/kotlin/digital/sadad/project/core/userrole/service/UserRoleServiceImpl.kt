package digital.sadad.project.core.userrole.service

import digital.sadad.project.core.userrole.repository.UserRoleCRUDRepository

class UserRoleServiceImpl(
    override val crudRepository: UserRoleCRUDRepository,
) : UserRoleService {
}