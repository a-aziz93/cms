package digital.sadad.project.core.userrole.service

import digital.sadad.project.core.userrole.repository.UserRoleRepository

class UserRoleServiceImpl(
    override val crudRepository: UserRoleRepository,
) : UserRoleService {
}