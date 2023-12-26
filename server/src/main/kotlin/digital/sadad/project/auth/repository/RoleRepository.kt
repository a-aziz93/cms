package digital.sadad.project.auth.repository

import digital.sadad.project.auth.model.Role
import digital.sadad.project.core.crud.repository.CrudRepository

interface RoleRepository: CrudRepository<Role, Long> {
}