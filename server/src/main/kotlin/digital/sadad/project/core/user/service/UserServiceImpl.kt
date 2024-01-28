package digital.sadad.project.core.user.service

import digital.sadad.project.core.user.repository.UserRepository

class UserServiceImpl(
    override val crudRepository: UserRepository,
) : UserService {
}