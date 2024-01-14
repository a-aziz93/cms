package digital.sadad.project.auth.repository

import digital.sadad.project.auth.entity.RoleEntity
import digital.sadad.project.core.crud.repository.CRUDRepository

interface RoleRepository: CRUDRepository<RoleEntity, Long> {
}