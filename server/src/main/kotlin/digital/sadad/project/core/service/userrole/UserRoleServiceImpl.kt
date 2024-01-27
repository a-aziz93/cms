package digital.sadad.project.core.service.userrole

import core.crud.model.userrole.entity.UserRoleEntity
import core.crud.repository.CRUDRepository
import digital.sadad.project.core.repository.userrole.UserRoleRepository
import digital.sadad.project.core.service.routerole.RouteRoleService

class UserRoleServiceImpl(
    override val crudRepository: UserRoleRepository,
) : UserRoleService {
}