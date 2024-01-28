package digital.sadad.project.core.user.service

import digital.sadad.project.core.user.repository.UserCRUDRepository

class UserServiceImpl(
    override val crudRepository: UserCRUDRepository,
) : UserService {
}