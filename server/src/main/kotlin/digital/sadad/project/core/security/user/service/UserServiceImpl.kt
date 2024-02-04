package digital.sadad.project.core.security.user.service

import digital.sadad.project.core.security.role.repository.RoleCRUDRepository
import digital.sadad.project.core.security.user.repository.UserCRUDRepository
import digital.sadad.project.core.security.userrole.repository.UserRoleCRUDRepository

class UserServiceImpl(
    override val crudRepository: UserCRUDRepository,
) : UserService {

}