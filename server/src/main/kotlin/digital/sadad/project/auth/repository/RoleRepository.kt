package digital.sadad.project.auth.repository

import digital.sadad.project.auth.model.Role
import digital.sadad.project.core.crud.repository.CRUDRepository

interface RoleRepository: CRUDRepository<Role, Long> {
}