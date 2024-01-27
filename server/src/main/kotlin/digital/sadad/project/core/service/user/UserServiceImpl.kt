package digital.sadad.project.core.service.user

import com.github.michaelbull.result.*
import core.crud.model.user.entity.UserEntity
import core.crud.repository.CRUDRepository
import core.crud.service.CRUDService
import digital.sadad.project.core.config.AppConfig
import digital.sadad.project.core.repository.user.UserRepository
import io.github.reactivecircus.cache4k.Cache

class UserServiceImpl(
    override val crudRepository: UserRepository,
) : UserService {
}