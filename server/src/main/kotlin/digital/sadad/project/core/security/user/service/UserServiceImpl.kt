package digital.sadad.project.core.security.user.service

import digital.sadad.project.core.security.user.repository.UserCRUDRepository

class UserServiceImpl(
    override val crudRepository: digital.sadad.project.core.security.user.repository.UserCRUDRepository,
) : digital.sadad.project.core.security.user.service.UserService {
}