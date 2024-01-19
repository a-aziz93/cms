package digital.sadad.project.auth.repository

import digital.sadad.project.auth.entity.RoleEntity
import digital.sadad.project.core.crud.repository.CRUDRepository
import digital.sadad.project.core.database.service.DatabaseService
import org.koin.core.annotation.Single

class RoleRepository: CRUDRepository<RoleEntity, Long> {
}