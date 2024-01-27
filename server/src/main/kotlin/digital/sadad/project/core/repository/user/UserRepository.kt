package digital.sadad.project.core.repository.user

import core.crud.model.user.entity.UserEntity
import digital.sadad.project.core.crud.repository.KotysaCRUDRepository

interface UserRepository : KotysaCRUDRepository<UserEntity, Long> {

}